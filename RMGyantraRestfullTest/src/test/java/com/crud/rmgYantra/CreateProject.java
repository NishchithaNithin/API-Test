package com.crud.rmgYantra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.crm.genericUtilities.BaseClass;
import com.crm.genericUtilities.EndPointLibrary;
import com.crm.genericUtilities.FileUtility;
import com.crm.genericUtilities.RestAssured_library;
import com.crm.genericUtilities.RetryAnalyserImptn;
import com.pojoclass.ProjectLibrary;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.List;

public class CreateProject
{
   @Test
   public void createProject()
   {
	   FileUtility flib=new FileUtility();
	
	   RestAssured_library rlib=new RestAssured_library();
 	   
	      
	      baseURI="http://localhost";
	        port= 8084;
	      
	        Response resp= given()
	        .body(plib)
	        .contentType(ContentType.JSON)
	        .when()
	        .post(EndPointLibrary.createProject);
	        //capture the projectID
	        String proid=rlib.getJSONPath(resp, "projectId");
	        		System.out.println(proid);
	         resp.then() .assertThat().statusCode(200).log().all();
	         
	         //create get request and pass proID
	        Response response= given().pathParam("pid",proid )
	         .when().get(EndPointLibrary.getSingleProjects+"{pid}");
	        
	        // getting projectId from response
			String projectIdFromResponse = rlib.getJSONPath(response, "projectId");

	         resp.then().assertThat().statusCode(200).log().all();	
	         
	      // Validating in GUI

	 		// launching the browser
	 		// entering url
	 		// entering valid details in field
	 		BaseClass base = new BaseClass();
	 		base.launchTheBrowser();

	 		// clicking on projects link
	 		base.driver.findElement(By.xpath("//a[text()='Projects']")).click();

	 		// selcting projectId for verifying and storing it in one variable
	 		List<WebElement> projectIdElement = base.driver.findElements(By.xpath("//td[1]"));

	 		// verification
	 		boolean temp = false;
	 		int count = 0;
	 		for (WebElement projectId : projectIdElement) {
	 			if (projectId.getText().equals(projectIdFromResponse)) {
	 				temp = true;
	 			}
	 			count++;
	 		}

	 		if (temp == true) {
	 			Assert.assertTrue(true);
	 			Reporter.log("<====Project is present in GUI====>", true);
	 		} else {
	 			Reporter.log("<====Project is not presented in GUI====>", true);
	 			Assert.assertTrue(false);
	 		}

	 		Reporter.log("Total no of projects checked = " + count, true);

	 		base.closeTheBrowser();

	 		// checking in database
	 		Reporter.log("Database verification starts", true);
	 		dLib.executeQuery("select * from project", 1, projectIdFromResponse);

	 		// deleting data in API
	 		when().delete(EndPointsLibrary.deleteProject + projectIdFromResponse).then().assertThat().statusCode(204)
	 				.time(Matchers.lessThan(2000L)).log().all();
	 		Reporter.log("Data deleted from API", true);

	 		// check data is deleted in gui or not
	 		// opening the browser
	 		// entering the url
	 		// giving valid details and login
	 		base.launchTheBrowser();

	 		// clicking on projects link
	 		base.driver.findElement(By.xpath("//a[text()='Projects']")).click();
	 		
	 		// selcting projectId for verifying and storing it in one variable
	 		List<WebElement> projectIdElement1 = base.driver.findElements(By.xpath("//td[1]"));

	 		// verification
	 		boolean flag = false;
	 		int countProject = 0;
	 		for (WebElement projectId1 : projectIdElement1) {
	 			if (projectId1.getText().equals(projectIdFromResponse)) {
	 				temp = true;
	 			}
	 			countProject++;
	 		}

	 		if (flag == false) {
	 			Assert.assertTrue(true);
	 			Reporter.log("<====Project is deleted, Not Present in GUI====>", true);
	 		} else {
	 			Reporter.log("<====Project is not deleted, It is Present in GUI====>", true);
	 			Assert.assertTrue(false);
	 		}

	 		Reporter.log("Total no of projects checked = " + count, true);

	 		base.closeTheBrowser();
	 		
	 		//checking data is deleted in database or not
	 		Reporter.log("Database verification starts",true);
	 		dLib.executeQuery("select * from project", 1, projectIdFromResponse);
	 		
	 		
	 	}
	 
	         
	        
	       
	      
	  }
   
}
