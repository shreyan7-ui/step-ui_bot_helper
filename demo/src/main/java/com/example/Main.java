package com.example;

import java.io.IOException;

import com.example.AdvancedSearch.AdvancedSearch;
import com.example.AdvancedSearch.AssociateEntry.BrandAgnostic;
import com.example.AdvancedSearch.AssociateEntry.DataManagement;
import com.example.AdvancedSearch.AssociateEntry.WHSEFacilityInfo;
import com.example.AdvancedSearch.LoginPage;
import com.example.AdvancedSearch.StepPopup;
import com.example.AdvancedSearch.VendorEntry.ItemInfo;
import com.example.AdvancedSearch.VendorEntry.PackagingHierarchy;
import com.example.AdvancedSearch.VendorEntry.VendorSupportInfo;
import com.example.Setup.ScreenshotUtil;
import com.example.Setup.setup;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {

        // Initialize setup
        setup base = new setup();
        base.ini_Driver();
        ScreenshotUtil sc = new ScreenshotUtil(setup.driver);

        // Login
        LoginPage login = new LoginPage(setup.driver);
        login.openURLAssociate();
        // login.openURLSupplier();
        login.login("dmnew", "7H3$5has3X");
        // login.login("user1","WinterO123");

        // Small wait (replace later with WebDriverWait)
        Thread.sleep(3000);

        // Handle Popup
        StepPopup popup = new StepPopup(setup.driver);
        popup.handlePopup();

        Thread.sleep(2000);

        // Navigate to Advanced Search
        popup.goToAdvancedSearch();

        AdvancedSearch search = new AdvancedSearch(setup.driver);
        search.search("033746291594");

        BrandAgnostic ba = new BrandAgnostic(setup.driver, sc);
        ba.bagnostic();

        DataManagement dm = new DataManagement(setup.driver, sc);
        dm.DataManage();

        WHSEFacilityInfo whse = new WHSEFacilityInfo(setup.driver, sc);
        whse.WHSE_Facility();

        PackagingHierarchy ph = new PackagingHierarchy(setup.driver);
        ph.Package();

        ItemInfo it = new ItemInfo(setup.driver, sc);
        it.iteminfo();

        VendorSupportInfo vsi = new VendorSupportInfo(setup.driver, sc);
        vsi.vendorSupport();
        // Close browser (optional for now)
        // base.tearDown();
    }
}