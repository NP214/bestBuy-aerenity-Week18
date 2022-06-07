package com.bestbuy.Utilities;

import com.bestbuy.steps.Utilitiesteps;
import com.bestbuy.testbase.VersionTestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class VersionTest extends VersionTestBase {
    @Steps
    Utilitiesteps utilitiesteps;
    @Test
    public void test001(){
     utilitiesteps.getVersions().log().all().statusCode(200);
    }

}
