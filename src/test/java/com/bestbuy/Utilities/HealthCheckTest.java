package com.bestbuy.Utilities;

import com.bestbuy.steps.Utilitiesteps;
import com.bestbuy.testbase.HealthcheckTestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class HealthCheckTest extends HealthcheckTestBase {
@Steps
    Utilitiesteps utilitiesteps;

    @Test


    public void test001(){
       utilitiesteps.healthCheck().log().all().statusCode(200);
    }
}
