/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.strategy;

import com.virtualfactory.data.GameData;
import com.virtualfactory.GameEngine;
import com.virtualfactory.entity.E_Operation;
import com.virtualfactory.entity.E_Purchase;
import com.virtualfactory.entity.E_Ship;
import com.virtualfactory.entity.E_TransportStore;
import com.virtualfactory.simpack.Sim;
import com.virtualfactory.simpack.SimEvent;
import com.virtualfactory.simpack.TOKEN;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.TypeActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 
 */
public class ManageEvents {
    private TOKEN tokenEvent = null;
    private ArrayList<EventStrategy> arrEvents;
//    private PriorityQueue<EventStrategy> arrPriorityQueueEvents;
    private GameData gameData;
    private GameEngine gameEngine;
    //First Integer = KEY, Pair: FistInteger = currentCount, SecondInteger = totalSize
    private Map<Integer,Pair<Integer,Integer>> arrEventsInstances;
    private ArrayList<Integer> arrIdActivities;
    private Comparator<EventStrategy> comparator = new Comparator<EventStrategy>(){
                    @Override
                    public int compare(EventStrategy a,EventStrategy b)
                    {
                      if (a.getPriorityQueue() > b.getPriorityQueue())
                      { return  1; }
                      else if (a.getPriorityQueue() < b.getPriorityQueue())
                      { return -1; }
                      else
                      { return  0; }
                    }
                };
        
    public ManageEvents(GameEngine gameEngine, GameData gameData){
        arrEvents = new ArrayList<EventStrategy>();
        arrEventsInstances = new HashMap<Integer, Pair<Integer,Integer>>();
        arrIdActivities = new ArrayList<Integer>();
        this.gameData = gameData;
        this.gameEngine = gameEngine;
        LoadEvents();
        //OMP.setNumThreads(4);
        //timeGame = System.currentTimeMillis()/1000.0;
    }
    
    private void LoadEvents(){
        int i=0;
        Iterable<E_Operation> operations = gameData.getMapOperation().values();
        for (E_Operation operation : operations){
            arrEvents.add(new OperationStrategy(i,operation,gameEngine));
            i++;
            arrEventsInstances.put(operation.getIdActivity(),new Pair(0,1));
            arrIdActivities.add(operation.getIdActivity());
        }
        Iterable<E_Purchase> purchases = gameData.getMapPurchase().values();
        for (E_Purchase purchase : purchases){
            arrEvents.add(new PurchaseStrategy(i,purchase,gameEngine));
            i++;
        }
        Iterable<E_Ship> ships = gameData.getMapShip().values();
        for (E_Ship ship : ships){
            arrEvents.add(new ShipStrategy(i,ship,gameEngine));
            i++;
            arrEventsInstances.put(ship.getIdActivity(),new Pair(0,1));
            arrIdActivities.add(ship.getIdActivity());
        }
        Iterable<E_TransportStore> transports = gameData.getMapTransport().values();
        for (E_TransportStore transport : transports){
            arrEvents.add(new TransportStrategy(i,transport,gameEngine));
            i++;
            arrEventsInstances.put(transport.getIdActivity(),new Pair(0,1));
            arrIdActivities.add(transport.getIdActivity());
        }
        Iterable<E_TransportStore> stores = gameData.getMapStore().values();
        for (E_TransportStore store : stores){
            arrEvents.add(new StoreStrategy(i,store,gameEngine));
            i++;
            arrEventsInstances.put(store.getIdActivity(),new Pair(0,1));
            arrIdActivities.add(store.getIdActivity());
        }
        updateEvents_OrderByPriority();
//        initialSize = i;
    }
    
    public void releaseResourcesEvent(){
        int indexEvent = (int)this.tokenEvent.getAttribute(0);
        //this.arrEvents.get(indexEvent).release();
        getEvent(indexEvent).release();
    }
    
    public void setStrategy(TOKEN tokenEvent){
        this.tokenEvent = tokenEvent;
    }
    
    public int executeEvent(){
        //int size = arrEvents.size();
//        double time;
//        System.out.println("Events created: " + size);
//        time = System.currentTimeMillis()/1000.0;
//        openMP_method();
//        timeOpenMP += System.currentTimeMillis()/1000.0 - time;
//        System.out.println("TIME CONSUMED FOR OPENMP: " + timeOpenMP);
        
//        time = System.currentTimeMillis()/1000.0;
        serial_method();
//        timeSerial += (System.currentTimeMillis()/1000.0 - time);
        //System.out.println("TIME CONSUMED FOR SERIAL: " + timeSerial);
        //System.out.println("CURRENT TIME GAME: " + (System.currentTimeMillis()/1000.0 - timeGame));
        return 0;
    }
/*Disabled openMP method    
    private void openMP_method(){
        
	int size = arrEvents.size();
        
        // OMP PARALLEL BLOCK BEGINS
        {
          __omp_Class0 __omp_Object0 = new __omp_Class0();
          // shared variables
          __omp_Object0.arrEventsSize = size;
          __omp_Object0.arrEventsJOMP = arrEvents;
          // firstprivate variables
          try {
            jomp.runtime.OMP.doParallel(__omp_Object0);
          } catch(Throwable __omp_exception) {
            System.err.println("OMP Warning: Illegal thread exception ignored!");
            System.err.println(__omp_exception);
          }
          // reduction variables
          // shared variables
          size = __omp_Object0.arrEventsSize;
          arrEvents = __omp_Object0.arrEventsJOMP;
        }
        // OMP PARALLEL BLOCK ENDS
    }
*/
    
    private void serial_method(){
        for (EventStrategy tempEvent : arrEvents){
            final TOKEN token = new TOKEN();
            token.setAttribute(0, tempEvent.getIdStrategy());
            token.setAttribute(1, gameEngine.getGeneralScreenController().getTimeFactor());
            double timeEvent = tempEvent.execute();
            if (timeEvent >= 0){
                if (tempEvent.getType().equals(TypeActivity.Purchase.toString())){
                    token.setAttribute(2, Params.simpackPurchase);
                }
                Sim.schedule(new SimEvent(Sim.time() + timeEvent, Params.endEvent, -1, token));
//                System.out.println(" - New event, current time:" + Sim.time() + " - FutureTime:" + (Sim.time() + timeEvent) + " - Time:" + timeEvent + " - Strategy:" + tempEvent.getIdStrategy() + "-" + tempEvent.getType() + " - token:" + token);
                tempEvent.addWorkCounter();
            }
            if (arrEventsInstances.containsKey(tempEvent.getIdActivity()) && tempEvent.getStateMachine().getStateIndex() > 0){
                arrEventsInstances.get(tempEvent.getIdActivity()).setFirst(arrEventsInstances.get(tempEvent.getIdActivity()).getFirst() + 1);
            }
        }
//        System.out.println("####### FIN ########");
//        for (int i=0; i<arrEventsInstances.size(); i++){
//            if (arrEventsInstances.containsKey(arrEvents.get(i).getIdActivity())){
//                if (arrEventsInstances.get(arrEvents.get(i).getIdActivity()).getFirst() == arrEventsInstances.get(arrEvents.get(i).getIdActivity()).getSecond()){
//                    arrEventsInstances.get(arrEvents.get(i).getIdActivity()).setSecond(arrEventsInstances.get(arrEvents.get(i).getIdActivity()).getSecond() + 1);
//                    if (arrEvents.get(i).getType().equals(TypeActivity.Operation.toString())){
//                        addEvent(new OperationStrategy(arrEvents.size(),(E_Operation)arrEvents.get(i).getData(),gameEngine));
//                    }else
//                    if (arrEvents.get(i).getType().equals(TypeActivity.Transport.toString())){
//                        addEvent(new TransportStrategy(arrEvents.size(),(E_TransportStore)arrEvents.get(i).getData(),gameEngine));
//                    }else
//                    if (arrEvents.get(i).getType().equals(TypeActivity.Store.toString())){
//                        addEvent(new StoreStrategy(arrEvents.size(),(E_TransportStore)arrEvents.get(i).getData(),gameEngine));
//                    }
//                    if (arrEvents.get(i).getType().equals(TypeActivity.Ship.toString())){
//                        addEvent(new ShipStrategy(arrEvents.size(),(E_Ship)arrEvents.get(i).getData(),gameEngine));
//                    }
//                }
//                arrEventsInstances.get(arrEvents.get(i).getIdActivity()).setFirst(0);
//            }            
//        }
        for (Integer tempIdActivity : arrIdActivities){
            Pair<Integer,Integer> pairEventInstance = arrEventsInstances.get(tempIdActivity);
            if (pairEventInstance.getFirst() == pairEventInstance.getSecond()){
                pairEventInstance.setSecond(pairEventInstance.getSecond() + 1);
                if (getEventByActivity(tempIdActivity).getType().equals(TypeActivity.Operation.toString())){
                    arrEvents.add(new OperationStrategy(arrEvents.size(),(E_Operation)getEventByActivity(tempIdActivity).getData(),gameEngine));
                }else
                if (getEventByActivity(tempIdActivity).getType().equals(TypeActivity.Transport.toString())){
                    arrEvents.add(new TransportStrategy(arrEvents.size(),(E_TransportStore)getEventByActivity(tempIdActivity).getData(),gameEngine));
                }else
                if (getEventByActivity(tempIdActivity).getType().equals(TypeActivity.Store.toString())){
                    arrEvents.add(new StoreStrategy(arrEvents.size(),(E_TransportStore)getEventByActivity(tempIdActivity).getData(),gameEngine));
                }
                if (getEventByActivity(tempIdActivity).getType().equals(TypeActivity.Ship.toString())){
                    arrEvents.add(new ShipStrategy(arrEvents.size(),(E_Ship)getEventByActivity(tempIdActivity).getData(),gameEngine));
                }
                updateEvents_OrderByPriority();
            }
            arrEventsInstances.get(tempIdActivity).setFirst(0);
        }
    }
    
/*        
    // OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
    private static class __omp_Class0 extends jomp.runtime.BusyTask {
        // shared variables
        int arrEventsSize;
        ArrayList<EventStrategy> arrEventsJOMP;
        // firstprivate variables
        // variables to hold results of reduction

        public void go(int __omp_me) throws Throwable {
        // firstprivate variables + init
        // private variables
        // reduction variables, init to default
        // OMP USER CODE BEGINS

            {
                { // OMP FOR BLOCK BEGINS
                // copy of firstprivate variables, initialized
                // copy of lastprivate variables
                // variables to hold result of reduction
                boolean amLast=false;
                {
                    // firstprivate variables + init
                    // [last]private variables
                    // reduction variables + init to default
                    // -------------------------------------
                    jomp.runtime.LoopData __omp_WholeData2 = new jomp.runtime.LoopData();
                    jomp.runtime.LoopData __omp_ChunkData1 = new jomp.runtime.LoopData();
                    __omp_WholeData2.start = (long)(0);
                    __omp_WholeData2.stop = (long)( arrEventsSize);
                    __omp_WholeData2.step = (long)(1);
                    jomp.runtime.OMP.setChunkStatic(__omp_WholeData2);
                    while(!__omp_ChunkData1.isLast && jomp.runtime.OMP.getLoopStatic(__omp_me, __omp_WholeData2, __omp_ChunkData1)) {
                        for(;;) {
                            if(__omp_WholeData2.step > 0) {
                                if(__omp_ChunkData1.stop > __omp_WholeData2.stop) __omp_ChunkData1.stop = __omp_WholeData2.stop;
                                if(__omp_ChunkData1.start >= __omp_WholeData2.stop) break;
                            } else {
                                if(__omp_ChunkData1.stop < __omp_WholeData2.stop) __omp_ChunkData1.stop = __omp_WholeData2.stop;
                                if(__omp_ChunkData1.start > __omp_WholeData2.stop) break;
                            }
                            for(int iRun = (int)__omp_ChunkData1.start; iRun < __omp_ChunkData1.stop; iRun += __omp_ChunkData1.step) {
                                // OMP USER CODE BEGINS
                                {
                                    System.out.println("********************** NoThread: " + OMP.getThreadNum() + " - In parallel: " + OMP.inParallel());
                                    if (arrEventsJOMP.get(iRun).validateResources()){
                                        int timeEvent = arrEventsJOMP.get(iRun).executeAlgorithm();
                                        final TOKEN token = new TOKEN();
                                        token.setAttribute(0, iRun);
                                        Sim.schedule(new SimEvent(Sim.time() + timeEvent, endEvent, -1, token));
                                    }       
                                }
                                // OMP USER CODE ENDS
                                if (iRun == (__omp_WholeData2.stop-1)) amLast = true;
                            } // of for 
                            if(__omp_ChunkData1.startStep == 0)
                            break;
                            __omp_ChunkData1.start += __omp_ChunkData1.startStep;
                            __omp_ChunkData1.stop += __omp_ChunkData1.startStep;
                        } // of for(;;)
                    } // of while
                    // call reducer
                    jomp.runtime.OMP.doBarrier(__omp_me);
                    // copy lastprivate variables out
                    if (amLast) {
                    }
                    }
                    // set global from lastprivate variables
                    if (amLast) {
                    }
                    // set global from reduction variables
                    if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
                    }
                } // OMP FOR BLOCK ENDS
            }
        // OMP USER CODE ENDS
      // call reducer
      // output to _rd_ copy
      if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
      }
      }
    }*/
    // OMP PARALLEL REGION INNER CLASS DEFINITION ENDS
    
    public ArrayList<EventStrategy> getArrEvents() {
        //return new ArrayList<EventStrategy>(arrPriorityQueueEvents);
        return arrEvents;
    }

//    public void setArrEvents(ArrayList<EventStrategy> arrEvents) {
//        this.arrEvents = arrEvents;
//    }
    
    public EventStrategy getEvent(int idStrategy){
        //ArrayList<EventStrategy> tempEvents = new ArrayList<EventStrategy>(arrPriorityQueueEvents);
        for (EventStrategy tempEvent : arrEvents){
            if (tempEvent.getIdStrategy() == idStrategy){
                return tempEvent;
            }
        }
        return null;
    }
    
    public EventStrategy getEventByActivity(int idActivity){
//        ArrayList<EventStrategy> tempEvents = new ArrayList<EventStrategy>(arrPriorityQueueEvents);
        for (EventStrategy tempEvent : arrEvents){
            if (tempEvent.getIdActivity() == idActivity){
                return tempEvent;
            }
        }
        return null;
    }
    
    public void updateEvents_OrderByPriority(){
        //arrPriorityQueueEvents = new PriorityQueue<EventStrategy>(arrPriorityQueueEvents);
        Collections.sort(arrEvents, comparator);
    }
}
