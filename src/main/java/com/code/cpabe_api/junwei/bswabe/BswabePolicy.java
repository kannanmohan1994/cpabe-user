package com.code.cpabe_api.junwei.bswabe;

import java.io.Serializable;
import java.util.ArrayList;

import it.unisa.dia.gas.jpbc.Element;

public class BswabePolicy implements Serializable{
	private static final long serialVersionUID = 1L;
	/* serialized */
	
	/* k=1 if leaf, otherwise threshould */
	int k;
	/* attribute string if leaf, otherwise null */
	String attr;
	Element c;			/* G_1 only for leaves */
	Element cp;		/* G_1 only for leaves */
	/* array of BswabePolicy and length is 0 for leaves */
	BswabePolicy[] children;
	
	/* only used during encryption */
	BswabePolynomial q;

	/* only used during decription */
	boolean satisfiable;
	int min_leaves;
	int attri;
	ArrayList<Integer> satl = new ArrayList<Integer>();
}
