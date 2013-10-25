/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.entity;

import edu.uprm.gaming.strategy.StateMachine;
import edu.uprm.gaming.utils.Params;
import edu.uprm.gaming.utils.Status;
import edu.uprm.gaming.utils.TypeActivity;
import edu.uprm.gaming.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author David
 */
public class E_Activity {
    private int idActivity;
    private int idPart;
    private String activityDescription;
    private TypeActivity typeActivity;
    private int idOrderActivity;
    private int idNextActivity;
    private int processingTime; //time in minutes
    private Status status = Status.Idle;
    private double percentageWorkPerHour = 0.0;
    private double numberWorkPerHour = 0.0;
    private double numberWorkPerDay = 0.0;
    private int countWorksPerHour = 0;
    private String activityState = "";
    private String activityStatus = "";
    private double costPerExecution;
    private StateMachine stateMachine = null;
    private ArrayList<Integer> arrSkillsRequired = null;
    private String machineCategory = "";
    private int priorityQueue;
    private int defaultValuePriority = 0;
    private Map<Integer, E_Operator> arrAssignedOperators = null;

    public int getDefaultValuePriority() {
        return defaultValuePriority;
    }

    public void setDefaultValuePriority(int defaultValuePriority) {
        this.defaultValuePriority = defaultValuePriority;
    }

    public Map<Integer, E_Operator> getArrAssignedOperators() {
        return arrAssignedOperators;
    }

    public void setArrAssignedOperators(Map<Integer, E_Operator> arrAssignedOperators) {
        this.arrAssignedOperators = arrAssignedOperators;
    }
    
    public int getPriorityQueue() {
        return priorityQueue;
    }

    public void setPriorityQueue(int priorityQueue) {
        this.priorityQueue = priorityQueue;
    }

    public ArrayList<Integer> getArrSkillsRequired() {
        return arrSkillsRequired;
    }

    public void setArrSkillsRequired(ArrayList<Integer> arrSkillsRequired) {
        this.arrSkillsRequired = arrSkillsRequired;
    }

    private void updateStatisticsValues()
    {
//        DecimalFormat df = new DecimalFormat("#.##");
//        percentageWorkPerHour = Double.parseDouble(df.format((countWorksPerHour * processingTime * 100.0)/60.0));
        percentageWorkPerHour = Utils.formatValue2Dec((countWorksPerHour * processingTime * 100.0)/60.0);
        numberWorkPerHour = countWorksPerHour;
        numberWorkPerDay = countWorksPerHour * Params.numberWorkHoursPerDay;
    }

    public StateMachine getStateMachine() {
        return stateMachine;
    }

    public void setStateMachine(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }
    
    public String getActivityState() {
        return activityState;
    }

    public void setActivityState(String activityState) {
        this.activityState = activityState;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }
    
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getCountWorksPerHour() {
        return countWorksPerHour;
    }

    public void setCountWorksPerHour(int countWorks) {
        if (countWorks == 0) updateStatisticsValues();
        this.countWorksPerHour = countWorks;
    }
    
    public double getNumberWorkPerDay() {
        return numberWorkPerDay;
    }

    public double getNumberWorkPerHour() {
        return numberWorkPerHour;
    }

    public double getPercentageWorkPerHour() {
        return percentageWorkPerHour;
    }
    
    public E_Activity() {
        arrAssignedOperators = new HashMap<Integer, E_Operator>();
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    public int getIdOrderActivity() {
        return idOrderActivity;
    }

    public void setIdOrderActivity(int idOrderActivity) {
        this.idOrderActivity = idOrderActivity;
    }

    public int getIdNextActivity() {
        return idNextActivity;
    }

    public void setIdNextActivity(int idNextActivity) {
        this.idNextActivity = idNextActivity;
    }

    public int getIdPart() {
        return idPart;
    }

    public void setIdPart(int idPart) {
        this.idPart = idPart;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    public TypeActivity getTypeActivity() {
        return typeActivity;
    }

    public void setTypeActivity(TypeActivity typeActivity) {
        this.typeActivity = typeActivity;
    }

    public double getCostPerExecution() {
        return costPerExecution;
    }

    public void setCostPerExecution(double costPerExecution) {
        this.costPerExecution = costPerExecution;
    }

    public String getMachineCategory() {
        return machineCategory;
    }

    public void setMachineCategory(String machineCategory) {
        this.machineCategory = machineCategory;
    }
    
}
