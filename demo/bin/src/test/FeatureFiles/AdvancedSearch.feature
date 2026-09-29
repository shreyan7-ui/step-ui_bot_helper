Feature: Advanced_Search

  Scenario: DM Portal
    Given I navigate to "https://adusa-pmdm-sit.mdm.stibosystems.com/webui/AssociatePortal/?kc_idp_hint="
    And I clear the content of input field "[UsernameDM]"
    And I enter into input field "[UsernameDM]" the value "(UsernameAssoc_DM)"
    And I clear the content of input field "[PasswordDM]"
    And I enter into input field "[PasswordDM]" the value "(PasswordAssoc_DM)"
    And I take screenshot
    And I click on "[Sign_DM]" button
    And I add wait seconds of "10"
    And I run custom code "StepPopUps" with arguments
      | "Test" |
    And I add wait seconds of "3"
    And I run custom code "AdvancedSearch" with arguments
      | "(UPC)" |
    And I take screenshot
    And I add wait seconds of "3"
    And I run custom code  "AdvancedSearchQuicklink"
      | "Test" |
    And I take screenshot
    And I add wait seconds of "3"
    And I run custom code  "AdvancedSearchBagnostic"
      | "Test" |
    And I take screenshot
    And I add wait seconds of "3"
    And I run custom code  "AdvancedSearchDataManagement"
      | "Test" |
    And I take screenshot
    And I add wait seconds of "3"
    And I run custom code  "AdvancedSearchWHSEFacilityInfo"
      | "Test" |
    And I take screenshot
    And I add wait seconds of "3"
    #And I run custom code "Packaging Hierarchy"
    # | "Test" |
    #And I take screenshot
   # And I add wait seconds of "3"
    #And I run custom code "Item Info"
     # | "Test" |
    #And I take screenshot
    #And I add wait seconds of "3"
   # And I run custom code "Vendor Support Info"
    #  | "Test" |
   # And I take screenshot
    #And I add wait seconds of "3"
   # And I run custom code "Vendor Information"
    #  | "Test" |
    #And I take screenshot
    #And I add wait seconds of "3"
    #And I run custom code "Vendor Information"
     # | "Test" |
    #And I take screenshot
    #And I add wait seconds of "3"
    #And I run custom code "Transformable Info"
     # | "Test" |
    #And I take screenshot
    #And I add wait seconds of "3"
