package com.virtualfactory.entity;

/**
 *
 * @author David
 */
public class E_Skill {
    private int idSkill;
    private String skillDescription;
//    private ArrayList<Integer> arrTypeOperation;

//    public ArrayList<Integer> getArrTypeOperation() {
//        return arrTypeOperation;
//    }
//
//    public void setArrTypeOperation(ArrayList<Integer> arrTypeOperation) {
//        this.arrTypeOperation = arrTypeOperation;
//    }

    public E_Skill(int idSkill, String skillDescription) {
        this.idSkill = idSkill;
        this.skillDescription = skillDescription;
    }

    public E_Skill() {
    }

    public int getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(int idSkill) {
        this.idSkill = idSkill;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }
    
}
