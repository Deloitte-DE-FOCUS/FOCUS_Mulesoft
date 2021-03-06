%dw 2.0
import * from dw::core::Strings
output application/java
---
vars.MemberAuthDetails filter ((((sizeOf (vars.ValidationError[$$].ErrorCode filter ($ != null)) )) == 0 )) map {
	"AUIndicator" : if ($.DMES_Authorization_ID__c == null) "A" else "U",
	"ExternalAuthNumber" : $.DPBHS_Authorization_Id__c,
	"DMESAuthNumber" : $.DMES_Authorization_ID__c,
	"MediaType" : "D",	
	"PAStatus" : "F",
	"PrintOption" : "N",
	"ProcessType" : $.DMES_Process_Type__c,
	"ProviderCheck" : $.DMES_Provider_Check__c default "N",
	"KeyedProviderId" : $.Service_Authorization__r.Provider__r.DEL_National_Prov_Id__c,//vars.ProviderNPI[(vars.MemberAuthServiceDetails[$.Client_PID__c].Provider__r.Id)[0]].NPI_Number__c[0],
	"ProviderId" : $.Service_Authorization__r.Provider__r.DEL_National_Prov_Id__c,//vars.ProviderNPI[(vars.MemberAuthServiceDetails[$.Client_PID__c].Provider__r.Id)[0]].NPI_Number__c[0],
	"TaxonomyCode" : vars.ProviderTaxonomyDetails[$.Service_Authorization__r.Provider__r.Id[0 to -4]].Taxonomy_Code_Calc__c[0],
	"ProviderName" : "",
	"EmergencySupply" : "N",
	"FinancialPayer" : "000000001",
	"ReceivedDate" : now() as String {format : "yyyyMMdd"},
	"24HourDrug" : "N",
	"HealthCheckOtherServices" : "N",
	"MemberId" : leftPad (vars.MemberAuthContactDetails[$.Client_PID__c].DEL_MCI__c[0],10,0),
	"MemberLast" : vars.MemberAuthContactDetails[$.Client_PID__c].LastName[0],
	"MemberFirst" : vars.MemberAuthContactDetails[$.Client_PID__c].FirstName[0],
	"AttendingPhysicianId" : "",// vars.MemberAuthServiceDetails[$.Client_PID__c].Provider_ID__c[0],
	"AttendingPhysicianTaxonomyCode" : "",//vars.MemberAuthServiceDetails[$.Client_PID__c].Provider__r.DEL_Provider_Taxonomy_Code__c,
	"AttendingPhysicianEmployeeVolunteer" : "",
	"AttendingPhysicianName" : "",//vars.MemberAuthServiceDetails[$.Client_PID__c].Provider__r.Name,
	"LineItem" : "001",
	"KeyedRenderingProviderNumber" : "",
	"RenderingProviderNumber" : "",
	"RenderingProviderTaxonomyCode" : "",
	"ServiceCodeType" : vars.LOVMapping[("DMES_LIST_ID-" ++ $.Service_Authorization__r.Service__r.List_Id_Code__c)][0],
	"ListId" : $.Service_Authorization__r.Service__r.List_Id_Code__c,//vars.MemberAuthServiceDetails[$.Client_PID__c].Service__r.List_Id_Code__c[0],
	"ServiceCode" : "",
	"ServiceDescription" : "",
	"Modifier1" : "",
	"Modifier2" : "",
	"Modifier3" : "",
	"Modifier4" : "",
	"Status" : if ($.DMES_Authorization_ID__c == null) "A" else (if ($.DMES_Status__c == "Cancelled") ("I") else "X"),
	"PaymentMethod" : "1",
	"RequestedUnits" : "",
	"RequestedDollars" : "",
	"AuthorizedUnits" : leftPad("999.00",19," "),
	"AuthorizedDollars" : "",
	"AuthorizedEffDate" : if ($.DMES_Authorization_Effective_Start_Date__c == null) ("        ") else ($.DMES_Authorization_Effective_Start_Date__c as Date as String {format : "yyyyMMdd"}),
	"AuthorizedEndDate" : if ($.DMES_Authorization_Effective_End_Date__c == null) ("        ") else ($.DMES_Authorization_Effective_End_Date__c as Date as String {format : "yyyyMMdd"}),
	"RequestedEffDate" : "        " as String {format : "yyyyMMdd"},
	"RequestedEndDate" : "        " as String {format : "yyyyMMdd"},
	"Frequency" : ""
}
