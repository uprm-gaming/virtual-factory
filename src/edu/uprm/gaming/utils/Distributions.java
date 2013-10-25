/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.utils;
import java.util.ArrayList;
import jsc.distributions.*;
/**
 *
 * @author David
 */
public class Distributions {
    public static String distBeta = "BETA";
    public static String distExponential = "EXPONENTIAL";
    public static String distGamma = "GAMMA";
    public static String distLogNormal = "LOGNORMAL";
    public static String distNormal = "NORMAL";
    public static String distUniform = "UNIFORM";
    public static String distWeibull = "WEIBULL";
    public static String distNone = "NONE";
    
    public static double calculateDist(String distrib, double p, double q)
    {
        if (distrib.equals(distBeta)) return new Beta(p, q).random();
        if (distrib.equals(distExponential)) return new Exponential(p).random();
        if (distrib.equals(distGamma)) return new Gamma(p, q).random();
        if (distrib.equals(distLogNormal)) return new Lognormal(p, q).random();
        if (distrib.equals(distNormal)) return new Normal(p, q).random();
        if (distrib.equals(distUniform)) return new Uniform(p, q).random();
        if (distrib.equals(distWeibull)) return new Weibull(p, q).random();
        return p;
    }
    
    public static ArrayList<String> getDistributions(){
        ArrayList<String> distributions = new ArrayList<String>();
        distributions.add(distNone);
        distributions.add(distBeta);
        distributions.add(distExponential);
        distributions.add(distGamma);
        distributions.add(distLogNormal);
        distributions.add(distNormal);
        distributions.add(distUniform);
        distributions.add(distWeibull);
        return distributions;
    }
}
