/*
 * jADM1 -- Java Implementation of Anaerobic Digestion Model No 1
 * ===============================================================
 *
 * Copyright 2015 Liam Pettigrew
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ********************************************************************************************
 */

package de.uni_erlangen.lstm.models.adm1;

import java.util.logging.Logger;

import de.uni_erlangen.lstm.file.CSVReader;
import de.uni_erlangen.lstm.file.CSVWriter;

/**
 * Specifies the dynamic state variables for initial conditions and influent/effluent
 * Modified from the BSM2 adjusted model for IAWQ AD Model No 1.
 * 
 * Special thanks to  Ulf Jeppsson, Christian Rosen and Darko Vrecko
 * for use of their Matlab code of the ADM1, 
 * developed when (around 2004) they were all working together at the 
 * Department of Industrial Electrical Engineering and Automation (IEA), Lund University, Sweden.
 * 
 * @author liampetti
 *
 */
public class StateVariables {	
	public final static Logger LOGGER = Logger.getLogger(StateVariables.class.getName());
	
	/*
	 * Digestor Influent
	 */
	private double S_su, S_aa, S_fa, S_va, S_bu, S_pro, S_ac, S_h2, S_ch4, S_IC, S_IN, S_I = 0.0;
	private double X_xc, X_ch, X_pr, X_li, X_su, X_aa, X_fa, X_c4, X_pro, X_ac, X_h2, X_I = 0.0;
	private double S_cat, S_an, S_hva, S_hbu, S_hpro, S_hac, S_hco3, S_nh3, S_gas_h2, S_gas_ch4, S_gas_co2 = 0.0;
	private double Q_D, T_D, gas_ch4, gas_vol, ph, S_co2, S_nh4 = 0.0;
	
	/**
	 * Read the outputs from a given CSV file
	 * 
	 * @param filename
	 */
	public void readVar(String filename) {
		CSVReader reader = new CSVReader(filename, ";");
		String[] outputs = reader.getNextString();
		double[] x = new double[outputs.length];
		for (int i=0;i<x.length;i++) {
			x[i] = Double.parseDouble(outputs[i]);
		}
		setVar(x);
	}
	
	/**
	 * Writes the current outputs to a CSV file
	 * 
	 * @param filename
	 */
	public void writeVar(String filename) {
		double[] x = getVar();
		CSVWriter writer = new CSVWriter();
		writer.WriteArray(filename, x, true);
	}
	
	/**
	 * Retrieves the outputs as an array
	 */
	public double[] getVar() {
		return new double[] { S_su, S_aa, S_fa, S_va, S_bu, S_pro, S_ac, S_h2, S_ch4,
				S_IC, S_IN, S_I, X_xc, X_ch, X_pr, X_li, X_su, X_aa, X_fa, X_c4, X_pro, X_ac,
				X_h2, X_I, S_cat, S_an, S_hva, S_hbu, S_hpro, S_hac, S_hco3, S_nh3, S_gas_h2, S_gas_ch4,
				S_gas_co2, Q_D, T_D, gas_ch4, gas_vol, ph, S_co2, S_nh4 };
	}
	
	/**
	 * Sets the outputs from an array
	 */
	public void setVar(double[] x) {
		S_su=x[0];
		S_aa=x[1];
		S_fa=x[2];
		S_va=x[3];
		S_bu=x[4];
		S_pro=x[5];
		S_ac=x[6];
		S_h2=x[7];
		S_ch4=x[8];
		S_IC=x[9];
		S_IN=x[10];
		S_I=x[11];
		X_xc=x[12]; 
		X_ch=x[13];
		X_pr=x[14];
		X_li=x[15];
		X_su=x[16];
		X_aa=x[17];
		X_fa=x[18];
		X_c4=x[19];
		X_pro=x[20];
		X_ac=x[21];
		X_h2=x[22];
		X_I=x[23]; 
		S_cat=x[24];
		S_an=x[25];
		// CSV file generated by Matlab implementation does not describe all variables
		if (x.length < 42) {
			Q_D=x[26];
			T_D=x[27];
			S_hva=0.0;
			S_hbu=0.0;
			S_hpro=0.0;
			S_hac=0.0;
			S_hco3=0.0;
			S_nh3=0.0;
			S_gas_h2=0.0;
			S_gas_ch4=0.0;
			S_gas_co2=0.0;
			gas_ch4=0.0;
			gas_vol=0.0;
			ph=0.0;
			S_co2=0.0;
			S_nh4=0.0;
		} else {
			S_hva=x[26];
			S_hbu=x[27];
			S_hpro=x[28];
			S_hac=x[29];
			S_hco3=x[30];
			S_nh3=x[31];
			S_gas_h2=x[32];
			S_gas_ch4=x[33];
			S_gas_co2=x[34];
			Q_D=x[35];
			T_D=x[36];
			gas_ch4=x[37];
			gas_vol=x[38];
			ph=x[39];
			S_co2=x[40];
			S_nh4=x[41];
		}
	}
	
	/**
	 * Getters and Setters for all individual variables
	 */
	public double getS_su() {
		return S_su;
	}

	public void setS_su(double s_su) {
		S_su = s_su;
	}

	public double getS_aa() {
		return S_aa;
	}

	public void setS_aa(double s_aa) {
		S_aa = s_aa;
	}

	public double getS_fa() {
		return S_fa;
	}

	public void setS_fa(double s_fa) {
		S_fa = s_fa;
	}

	public double getS_va() {
		return S_va;
	}

	public void setS_va(double s_va) {
		S_va = s_va;
	}

	public double getS_bu() {
		return S_bu;
	}

	public void setS_bu(double s_bu) {
		S_bu = s_bu;
	}

	public double getS_pro() {
		return S_pro;
	}

	public void setS_pro(double s_pro) {
		S_pro = s_pro;
	}

	public double getS_ac() {
		return S_ac;
	}

	public void setS_ac(double s_ac) {
		S_ac = s_ac;
	}

	public double getS_h2() {
		return S_h2;
	}

	public void setS_h2(double s_h2) {
		S_h2 = s_h2;
	}

	public double getS_ch4() {
		return S_ch4;
	}

	public void setS_ch4(double s_ch4) {
		S_ch4 = s_ch4;
	}

	public double getS_IC() {
		return S_IC;
	}

	public void setS_IC(double s_IC) {
		S_IC = s_IC;
	}

	public double getS_IN() {
		return S_IN;
	}

	public void setS_IN(double s_IN) {
		S_IN = s_IN;
	}

	public double getS_I() {
		return S_I;
	}

	public void setS_I(double s_I) {
		S_I = s_I;
	}

	public double getX_xc() {
		return X_xc;
	}

	public void setX_xc(double x_xc) {
		X_xc = x_xc;
	}

	public double getX_ch() {
		return X_ch;
	}

	public void setX_ch(double x_ch) {
		X_ch = x_ch;
	}

	public double getX_pr() {
		return X_pr;
	}

	public void setX_pr(double x_pr) {
		X_pr = x_pr;
	}

	public double getX_li() {
		return X_li;
	}

	public void setX_li(double x_li) {
		X_li = x_li;
	}

	public double getX_su() {
		return X_su;
	}

	public void setX_su(double x_su) {
		X_su = x_su;
	}

	public double getX_aa() {
		return X_aa;
	}

	public void setX_aa(double x_aa) {
		X_aa = x_aa;
	}

	public double getX_fa() {
		return X_fa;
	}

	public void setX_fa(double x_fa) {
		X_fa = x_fa;
	}

	public double getX_c4() {
		return X_c4;
	}

	public void setX_c4(double x_c4) {
		X_c4 = x_c4;
	}

	public double getX_pro() {
		return X_pro;
	}

	public void setX_pro(double x_pro) {
		X_pro = x_pro;
	}

	public double getX_ac() {
		return X_ac;
	}

	public void setX_ac(double x_ac) {
		X_ac = x_ac;
	}

	public double getX_h2() {
		return X_h2;
	}

	public void setX_h2(double x_h2) {
		X_h2 = x_h2;
	}

	public double getX_I() {
		return X_I;
	}

	public void setX_I(double x_I) {
		X_I = x_I;
	}

	public double getS_cat() {
		return S_cat;
	}

	public void setS_cat(double s_cat) {
		S_cat = s_cat;
	}

	public double getS_an() {
		return S_an;
	}

	public void setS_an(double s_an) {
		S_an = s_an;
	}

	public double getS_hva() {
		return S_hva;
	}

	public void setS_hva(double s_hva) {
		S_hva = s_hva;
	}

	public double getS_hbu() {
		return S_hbu;
	}

	public void setS_hbu(double s_hbu) {
		S_hbu = s_hbu;
	}

	public double getS_hpro() {
		return S_hpro;
	}

	public void setS_hpro(double s_hpro) {
		S_hpro = s_hpro;
	}

	public double getS_hac() {
		return S_hac;
	}

	public void setS_hac(double s_hac) {
		S_hac = s_hac;
	}

	public double getS_hco3() {
		return S_hco3;
	}

	public void setS_hco3(double s_hco3) {
		S_hco3 = s_hco3;
	}

	public double getS_nh3() {
		return S_nh3;
	}

	public void setS_nh3(double s_nh3) {
		S_nh3 = s_nh3;
	}

	public double getS_gas_h2() {
		return S_gas_h2;
	}

	public void setS_gas_h2(double s_gas_h2) {
		S_gas_h2 = s_gas_h2;
	}

	public double getS_gas_ch4() {
		return S_gas_ch4;
	}

	public void setS_gas_ch4(double s_gas_ch4) {
		S_gas_ch4 = s_gas_ch4;
	}

	public double getS_gas_co2() {
		return S_gas_co2;
	}

	public void setS_gas_co2(double s_gas_co2) {
		S_gas_co2 = s_gas_co2;
	}

	public double getQ_D() {
		return Q_D;
	}

	public void setQ_D(double q_D) {
		Q_D = q_D;
	}

	public double getT_D() {
		return T_D;
	}

	public void setT_D(double t_D) {
		T_D = t_D;
	}

	public double getQ_Gas() {
		return gas_ch4;
	}

	public void setQ_Gas(double q_gas) {
		gas_ch4 = q_gas;
	}

	public double getP_Ch4() {
		return gas_vol;
	}

	public void setP_Ch4(double p_ch4) {
		gas_vol = p_ch4;
	}

	public double getPh() {
		return ph;
	}

	public void setPh(double ph) {
		this.ph = ph;
	}

	public double getGas_ch4() {
		return gas_ch4;
	}

	public void setGas_ch4(double gas_ch4) {
		this.gas_ch4 = gas_ch4;
	}

	public double getGas_vol() {
		return gas_vol;
	}

	public void setGas_vol(double gas_vol) {
		this.gas_vol = gas_vol;
	}

	public double getS_co2() {
		return S_co2;
	}

	public void setS_co2(double s_co2) {
		S_co2 = s_co2;
	}

	public double getS_nh4() {
		return S_nh4;
	}

	public void setS_nh4(double s_nh4) {
		S_nh4 = s_nh4;
	}
}
