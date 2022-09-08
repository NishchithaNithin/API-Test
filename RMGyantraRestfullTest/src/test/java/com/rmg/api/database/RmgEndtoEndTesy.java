package com.rmg.api.database;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.crm.genericUtilities.BaseAPIClass;
import com.crm.genericUtilities.BaseClass;
//import com.crm.genericUtilities.DataBaseUtility;
import com.crm.genericUtilities.EndPointLibrary;
import com.pojoclass.ProjectLibrary;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.List;
public class RmgEndtoEndTesy extends BaseAPIClass {
	@Test
	public void createProjectVerifyInGUIandDB() throws Throwable
	{
		ProjectLibrary plib=new ProjectLibrary("modi", "india"+jlib.getRandomNumber(), "onGoing", 20);
		given()
		.body(plib)
		.contentType(ContentType.JSON)
		.when().post(EndPointLibrary.createProject)
		.then().assertThat().log().all(); 

		//create get request and pass proID
		Response response= given().pathParam("pid",proid )
				.when().get(EndPointLibrary.getSingleProjects+"{pid}");

		// getting projectId from response
		String projectIdFromResponse = rlib.getJSONPath(response, "projectId");

		response.then().assertThat().statusCode(200).log().all();	

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
		dlib.executeQuery("select * from project", 1, projectIdFromResponse);

		// deleting data in API
		when().delete(EndPointLibrary.DeleteProject + projectIdFromResponse).then().assertThat().statusCode(204).log().all();
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
		dlib.executeQuery("select * from project", 1, projectIdFromResponse);


	}


	/* dlib.executeQuery("select * from project", 1,"TY_PROJ_2004");

	     BaseClass baseclass=new BaseClass();*/


}

}
