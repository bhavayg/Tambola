package com.tambola.demo.service;

import org.springframework.stereotype.Component;

@Component
public class RandomService {
	public String randomString(int n){
  
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
  
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
  
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
  
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                          .charAt(index));
        }
  
        return sb.toString();
    }
	
	public int[][] ticket(){
		int[][] ticket = new int[5][3];
		for(int i=0;i<5;i++) {
			for(int j=0;j<3;j++) {
				ticket[i][j]=(int)(49*Math.random()+1);
			}
		}
		return ticket;	
	}
	public int[][] updateTicket(int[][] ticket,int number){
		for(int i=0;i<5;i++) {
			for(int j=0;j<3;j++) {
				if(ticket[i][j]>0) {
					if(ticket[i][j]==number)
						ticket[i][j]=-1*ticket[i][j];
				}
				ticket[i][j]=(int)((49*Math.random())+1);
			}
		}
		return ticket;	
	}
	
/*	public static void main(String[] args)
    {
  
        // Get the size n
        int n = 20;
        RandomService r =new RandomService();
        // Get and display the alphanumeric string
        String s1 = "abcd";
        String s2 = "abcd";
        System.out.println(s1==s2);
        if(s1==s2)
        	System.out.println(r.randomString(n));
        else
        System.out.println("edxsw");
    }*/
}
