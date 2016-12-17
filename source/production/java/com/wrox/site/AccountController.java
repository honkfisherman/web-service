package com.wrox.site;

import com.wrox.config.annotation.WebController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

@WebController
public class AccountController
{
    @Inject AccountService accountService;

    @RequestMapping(value = "account/list", method = RequestMethod.GET)
    public String list(Map<String, Object> model)
    {
    	System.out.println("Calling jsp inside list method");
    	int a=5, b=7;
    	int c = a += 2 * 3 + b--;
    	System.out.println("c=" + c);
    	
    	Object[] arr1 = {"junk1","junk2"};
    	Object[] result = (Object[])arr1.clone();
    	Arrays.fill(result, null);
    	Random random = new Random();
    	for (int i=0; i<arr1.length; i++)
    	{
    	int randomIndex;
    	do{
    		randomIndex = random.nextInt(arr1.length);
    		
    	}while (result[randomIndex] != null);
    	
    	result[randomIndex] = arr1[i];
    	System.out.println("Finish result");
    	}
    	
        model.put("accounts", this.accountService.getAllAccounts());
        return "account/list.jsp";
        // Use url http://localhost:8080/financials/account/list  (Can't have a / at the end)
    }
    
    @RequestMapping(value = "account/listing", method = RequestMethod.GET)
    public String listing(Map<String, Object> model)
    {
    	System.out.println("Inside listing method");
        //model.put("accounts", this.accountService.getAllAccounts());
        return "account/listing.html";
        // Use url http://localhost:8080/financials/account/listing  (Can't have a / at the end)
        //return "account/list";
    }
    
    @RequestMapping(value = "account/account_listing", method = RequestMethod.GET)
    public String account_listing(Map<String, Object> model)
    {
    	System.out.println("Inside account_listing method");
        //model.put("accounts", this.accountService.getAllAccounts());
        return "account/account_listing.html";
    }
    
    @RequestMapping(value = "account/app/cust_listing", method = RequestMethod.GET)
    public String cust_listing(Map<String, Object> model)
    {
    	System.out.println("Inside app/cust_listing method");
        return "account/app/cust_listing.html";
    }
}
