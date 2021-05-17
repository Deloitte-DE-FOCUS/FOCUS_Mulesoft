%dw 1.0
%output application/java
%function getDSTAdjustedDatetime(standarddatetime) (standarddatetime - |PT4H| )  when  (isDateInDST(((standarddatetime - |PT4H|) ) as :date,"US/Eastern") == true) otherwise (standarddatetime - |PT5H|)
---
{	
	"Id": payload.AssessmentExtensionSFId,
	"Screener_Created_Date__c": (flowVars.inputData.DateCreated as :datetime + |PT4H|) when flowVars.inputData.DateCreated != null otherwise null,
	// as :string {format:"yyyy-MM-dd'T'HH:mm:ss.SSS"},
	"Screener_Title__c": flowVars.inputData.AssessmentTitle,
	"Screener_Completed_Date__c": (flowVars.inputData.DateCompleted as :datetime + |PT4H|) when flowVars.inputData.DateCompleted != null otherwise null,
	"Screener_Type_Case__c": flowVars.inputData.AssessmentType,
	"Vant4gePoint_Assessment_ID__c": flowVars.inputData.OffenderAssessmentID as :string,
	//"Completed_By__c": flowVars.inputData.CompletedBy,
	("Overall_Level_Risk_to_Reoffend__c": flowVars.inputData.Properties.OverallRiskLevel) when (flowVars.inputData.Properties.OverallRiskLevel != null and flowVars.inputData.Properties.OverallRiskLevel != ""),
	("Criminogenic_Need__c": flowVars.inputData.Properties.CriminogenicNeed1) when flowVars.inputData.Properties.CriminogenicNeed1 != null and flowVars.inputData.Properties.CriminogenicNeed1 != "",
	("Protective_Factor__c" : flowVars.inputData.Properties.ProtectiveFactor1) when flowVars.inputData.Properties.CriminogenicNeed1 != null and flowVars.inputData.Properties.ProtectiveFactor != "",
	"Status__c": "Completed",
	"Q1_Age_at_First_Offense__c" : flowVars.inputData.QuestionResponses["History of Juvenile Justice Involvement"][0].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["History of Juvenile Justice Involvement"][0].Question == "Age at First Offense:") otherwise "",
	"Q2_Misdemeanor_Referrals__c" : flowVars.inputData.QuestionResponses["History of Juvenile Justice Involvement"][1].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["History of Juvenile Justice Involvement"][1].Question == "Misdemeanor Referrals:") otherwise "",
	"Q3_Felony_Referrals__c" : flowVars.inputData.QuestionResponses["History of Juvenile Justice Involvement"][2].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["History of Juvenile Justice Involvement"][2].Question == "Felony Referrals:") otherwise "",
	"Q4_Weapon_Referrals__c" : flowVars.inputData.QuestionResponses["History of Juvenile Justice Involvement"][3].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["History of Juvenile Justice Involvement"][3].Question == "Weapon referrals:") otherwise "",
	"Q5_Against_person_misdemeanor_referrals__c" : flowVars.inputData.QuestionResponses["History of Juvenile Justice Involvement"][4].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["History of Juvenile Justice Involvement"][4].Question == "Against-person misdemeanor referrals:") otherwise "",
	"Q6_Again_person_felony_referrals__c" : flowVars.inputData.QuestionResponses["History of Juvenile Justice Involvement"][5].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["History of Juvenile Justice Involvement"][5].Question == "Against-person felony referrals:") otherwise "",
	"Q7_Misdemeanor_referrals_ISB__c" : flowVars.inputData.QuestionResponses["History of Juvenile Justice Involvement"][6].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["History of Juvenile Justice Involvement"][6].Question == "Misdemeanor referrals for acts involving inappropriate sexual behavior:") otherwise "",
	"Q8_Felony_referrals_ISB__c" : flowVars.inputData.QuestionResponses["History of Juvenile Justice Involvement"][7].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["History of Juvenile Justice Involvement"][7].Question == "Felony referrals for acts involving inappropriate sexual behavior:") otherwise "",
	"Q9_Confinements_in_detention_48_hours__c" : flowVars.inputData.QuestionResponses["Residential Placement & Capias"][0].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Residential Placement & Capias"][0].Question == "Confinements in detention where youth was held for at least 48 hours:") otherwise "",
	"Q10_Capias_for_FTA_in_court__c" : flowVars.inputData.QuestionResponses["Residential Placement & Capias"][1].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Residential Placement & Capias"][1].Question == "Capias for failure-to-appear in court:") otherwise "",
	"Q11_Capias_for_absconding_from_probation__c" : flowVars.inputData.QuestionResponses["Residential Placement & Capias"][2].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Residential Placement & Capias"][2].Question == "Capias for absconding from supervision:") otherwise "",
	"Q12_School_status_last_6_months__c" : flowVars.inputData.QuestionResponses["Education"][0].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][0].Question == "Youth has been enrolled in school during the last 6 months in the community, regardless of attendance:") otherwise "",
	"Q13_Current_school_enrollment_status__c" : flowVars.inputData.QuestionResponses["Education"][1].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][1].Question == "Youth's current school enrollment status either in the community or just prior to residential admission (regardless of attendance):") otherwise "",
	"Q14_Conduct_in_the_most_recent_term__c" : flowVars.inputData.QuestionResponses["Education"][2].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][2].Question == "Youth's conduct in the most recent term:") otherwise "",
	"Q15_Suspensions_expulsions_1st_grade_on__c" : (("No school suspensions or expulsions in the most recent term;" when (flowVars.inputData.QuestionResponses["Education"][3].Responses[0] map $.Response contains "No school suspensions or expulsions in the most recent term") otherwise "") ++
		("In school suspension(s);" when (flowVars.inputData.QuestionResponses["Education"][3].Responses map $.Response contains "In school suspension(s)") otherwise "") ++
		("Out of school suspension(s);" when (flowVars.inputData.QuestionResponses["Education"][3].Responses map $.Response contains "Out of school suspension(s)") otherwise "") ++
		("Expulsions;" when (flowVars.inputData.QuestionResponses["Education"][3].Responses map $.Response contains "Expulsions") otherwise "") ),
	"Question_15_Additional_Answers__c" : (("1;" when (flowVars.inputData.QuestionResponses["Education"][3].Responses map $.Response contains "1") otherwise "") ++ 
		("2;" when (flowVars.inputData.QuestionResponses["Education"][3].Responses map $.Response contains "2") otherwise "") ++ 
		("3;" when (flowVars.inputData.QuestionResponses["Education"][3].Responses map $.Response contains "3") otherwise "") ++ 
		("4;" when (flowVars.inputData.QuestionResponses["Education"][3].Responses map $.Response contains "4") otherwise "") ++
		("5;" when (flowVars.inputData.QuestionResponses["Education"][3].Responses map $.Response contains "5") otherwise "") ++ 
		("6;" when (flowVars.inputData.QuestionResponses["Education"][3].Responses map $.Response contains "6") otherwise "") ++ 
		("7+" when (flowVars.inputData.QuestionResponses["Education"][3].Responses map $.Response contains "7+") otherwise "")	),
	"Q16_Suspensions_expulsions_recent_term__c" : (("No school suspensions or expulsions in the most recent term;" when (flowVars.inputData.QuestionResponses["Education"][4].Responses map $.Response contains "No school suspensions or expulsions in the most recent term") otherwise "") ++
		("In school suspension(s);" when (flowVars.inputData.QuestionResponses["Education"][4].Responses map $.Response contains "In school suspension(s)") otherwise "") ++
		("Out of school suspension(s);" when (flowVars.inputData.QuestionResponses["Education"][4].Responses map $.Response contains "Out of school suspension(s)") otherwise "") ++
		("Expulsion(s);" when (flowVars.inputData.QuestionResponses["Education"][4].Responses map $.Response contains "Expulsion(s)") otherwise "") ),
	"Question_16_Additional_Answers__c" : (("1;" when (flowVars.inputData.QuestionResponses["Education"][4].Responses map $.Response contains "1") otherwise "") ++ 
		("2;" when (flowVars.inputData.QuestionResponses["Education"][4].Responses map $.Response contains "2") otherwise "") ++ 
		("2 or 3;" when (flowVars.inputData.QuestionResponses["Education"][4].Responses map $.Response contains "2 or 3") otherwise "") ++ 
		("3+;" when (flowVars.inputData.QuestionResponses["Education"][4].Responses map $.Response contains "3+") otherwise "") ++
		("More than 3;" when (flowVars.inputData.QuestionResponses["Education"][4].Responses map $.Response contains "More than 3") otherwise "") ),
	"Q17_Youth_belief_in_getting_an_education__c" : flowVars.inputData.QuestionResponses["Education"][5].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][5].Question == "Youth believes there is value in getting an education:") otherwise "",
	"Q18_Youth_belief_school_is_encouraging__c" : flowVars.inputData.QuestionResponses["Education"][6].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][6].Question == "Youth believes school provides an encouraging environment for him or her:") otherwise "",
	"Q19_Staff_that_youth_likes__c" : flowVars.inputData.QuestionResponses["Education"][7].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][7].Question == "Teachers, instructors, staff, or coaches whom the youth likes, or with whom the youth feels comfortable talking:") otherwise "",
	"Q20_Attendance_in_the_most_recent_term__c" : flowVars.inputData.QuestionResponses["Education"][8].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][8].Question == "Youth's attendance in the most recent term:") otherwise "",
	"Q21_Youth_grades_in_recent_school_term__c" : flowVars.inputData.QuestionResponses["Education"][9].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][9].Question == "Youth's academic performance in the most recent school term:") otherwise "",
	"Q22_Will_youth_stay_in_school_graduate__c" : flowVars.inputData.QuestionResponses["Education"][10].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][10].Question == "Interviewer's assessment of likelihood the youth will stay in and graduate from high school or an equivalent vocational school:") otherwise "",
	"Q23_Currently_kicked_out_of_home_runaway__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][0].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][0].Question == 'Youth is currently kicked out of home or is a runaway:') otherwise "",
	"Q24_History_kicked_out_of_home_runaway__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][1].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][1].Question == 'History of running away or getting kicked out of home:') otherwise "",
	"Q25_History_of_being_a_victim_of_neglect__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][2].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][2].Question == 'History of being a victim of neglect') otherwise "",
	"Q26_Who_is_youth_living_with__c" : (("Living alone;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Living alone") otherwise "") ++  
		("Parent(s);" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Parent(s)") otherwise "") ++ 
		("Other relative(s);" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Other relative(s)") otherwise "") ++ 
		("Non-relative(s);" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Non-relative(s)") otherwise "") ++  
		("Foster/group home;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Foster/group home") otherwise "") ++  
		("Transient (street/incarcerated)" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Transient (street/incarcerated)") otherwise ""))  ,
	"Question_26_Additional_Answers__c" : (("Biological/Non-biological mother;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Biological/Non-biological mother") otherwise "") ++  
		("Biological/Non-biological father;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Biological/Non-biological father") otherwise "") ++  
		("Long-term parental partner(s);" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Long-term parental partner(s)") otherwise "") ++  
		("Short-term parental partner(s);" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Short-term parental partner(s)") otherwise "") ++  
		("Grandparent(s);" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Grandparent(s)") otherwise "") ++ 
		("Older sibling(s);" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Older sibling(s)") otherwise "") ++  
		("Younger sibling(s);" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Younger sibling(s)") otherwise "") ++  
		("Youth's child;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Youth's child") otherwise "") ++  
		("Other relative;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Other relative") otherwise "") ++ 
		("Family friend;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Family friend") otherwise "") ++ 
		("Parent's roommate;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Parent's roommate") otherwise "") ++ 
		("Youth's romantic partner;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Youth's romantic partner") otherwise "") ++    
		("Youth's friend;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Youth's friend") otherwise "") ++  
		("Other non-relative" when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses map $.Response contains "Other Non-relative") otherwise "")),  		
	"Question_26_Relative_Name__c" : payload."Question_26_Relative_Name__c"[0] default "",   
	"Question_26_Non_Relative_Name__c" : payload."Question_26_Non_Relative_Name__c"[0] default "",
	"Q27_Problem_history_of_parents__c" : ((flowVars.inputData.QuestionResponses["Family & Social Support"][4].Responses[0].Response default "") ++ ";"  ++ (flowVars.inputData.QuestionResponses["Family & Social Support"][4].Responses[1].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Family & Social Support"][4].Responses[2].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Family & Social Support"][4].Responses[3].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Family & Social Support"][4].Responses[4].Response default "")) 
		when (flowVars.inputData.QuestionResponses["Family & Social Support"][4].Question == 'Problem history of adults who are currently involved with the household:') otherwise "",
	"Q28_Problem_history_of_siblings__c" : ((flowVars.inputData.QuestionResponses["Family & Social Support"][5].Responses[0].Response default "" ) ++ ";" ++ (flowVars.inputData.QuestionResponses["Family & Social Support"][5].Responses[1].Response default "" ) ++ ";" ++ (flowVars.inputData.QuestionResponses["Family & Social Support"][5].Responses[2].Response default "" ) ++ ";" ++ (flowVars.inputData.QuestionResponses["Family & Social Support"][5].Responses[3].Response default "" ) ++ ";" ++ (flowVars.inputData.QuestionResponses["Family & Social Support"][5].Responses[4].Response default "" ) )  
		when (flowVars.inputData.QuestionResponses["Family & Social Support"][5].Question == 'Problem history of siblings who are currently involved with the household:') otherwise "",
	"Q29_Imprisonment_history_household__c" : (("No jail/imprisonment history in family;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "No jail/imprisonment history in family") otherwise "") ++  
	("Mother/female caretaker;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Mother/female caretaker") otherwise "") ++ 
	("Father/male caretaker;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Father/male caretaker") otherwise "") ++  
	("Older sibling;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Older sibling") otherwise "") ++ 
	("Younger sibling;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Younger sibling") otherwise "") ++  
	("Other member" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Other member") otherwise "")),
	"Question_29_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses[-1].Response 
		when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Other member") otherwise "",
	"Q30_Imprisonment_history_household_3mos__c" : (("No jail/imprisonment history in family;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][7].Responses map $.Response contains "No jail/imprisonment history in family") otherwise "") ++ 
		("Mother/female caretaker;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][7].Responses map $.Response contains "Mother/female caretaker") otherwise "") ++ 
		("Father/male caretaker;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][7].Responses map $.Response contains "Father/male caretaker") otherwise "") ++  
		("Older sibling;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][7].Responses map $.Response contains "Older sibling") otherwise "") ++
		("Younger sibling;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][7].Responses map $.Response contains "Younger sibling") otherwise "") ++  
		("Other member" when (flowVars.inputData.QuestionResponses["Family & Social Support"][7].Responses map $.Response contains "Other member") otherwise "")) ,
	"Question_30_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][7].Responses[-1].Response 
		when (flowVars.inputData.QuestionResponses["Family & Social Support"][7].Responses map $.Response contains "Other member") otherwise "",
	"Q31_Parental_supervision__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][8].Responses[0].Response 
		when (flowVars.inputData.QuestionResponses["Family & Social Support"][8].Question == 'Parental supervision:') otherwise "",
	"Q32_Parental_authority_and_control__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][9].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][9].Question == 'Parental authority and control:') otherwise "",
	"Q33_Consistent_appropriate_punishment__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][10].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][10].Question == 'Consistent appropriate punishment for bad behavior:') otherwise "",
	"Q34_Consistent_appropriate_rewards__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][11].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][11].Question == 'Consistent appropriate rewards for good behavior:') otherwise "",
	"Q35_Family_willingness_to_support_youth__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][12].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][12].Question == "Family's willingness to help support youth:") otherwise "",
	"Q36_Family_with_whom_youth_feels_close__c" : (("Feels close to mother/female caretaker;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][13].Responses map $.Response contains "Feels close to mother/female caretaker") otherwise "") ++  
		("Feels close to father/male caretaker;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][13].Responses map $.Response contains "Feels close to father/male caretaker") otherwise "") ++ 
 		("Feels close to male sibling;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][13].Responses map $.Response contains "Feels close to male sibling") otherwise "") ++ 
 		("Feels close to female sibling;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][13].Responses map $.Response contains "Feels close to female sibling") otherwise "") ++  
 		("Feels close to extended family;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][13].Responses map $.Response contains "Feels close to extended family") otherwise "") ++ 
 		("Does not feel close to any family member;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][13].Responses map $.Response contains "Does not feel close to any family member") otherwise "") ++ 
 		("Other" when (flowVars.inputData.QuestionResponses["Family & Social Support"][13].Responses map $.Response contains "Other") otherwise "")),
	"Question_36_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][13].Responses[-1].Response 
		when (flowVars.inputData.QuestionResponses["Family & Social Support"][13].Responses map $.Response contains "Other") otherwise "",
	"Q37_Level_of_conflict_between_family__c" : ((flowVars.inputData.QuestionResponses["Family & Social Support"][14].Responses[0].Response default "") ) 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][14].Question == 'Level of conflict between parents, between youth and parents, or among siblings:') otherwise "",
	"Question_37_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][14].Responses[1].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][14].Responses[-1].Response != null) otherwise "",
	"Q38_Support_network_for_family__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][15].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][15].Question == 'Support network for family:') otherwise "",
	"Q39_Annual_income_of_youth_family__c" : "Unknown" when (flowVars.inputData.QuestionResponses["Family & Social Support"][16].Responses[0].Response == "Unknown") otherwise 
	flowVars.inputData.QuestionResponses["Family & Social Support"][16].Responses[2].Response,
	"Question_39_of_Persons_in_Household__c" : "" when (flowVars.inputData.QuestionResponses["Family & Social Support"][16].Responses[0].Response == "Unknown") otherwise 
	flowVars.inputData.QuestionResponses["Family & Social Support"][16].Responses[0].Response,
	"Question_39_Annual_Income__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][16].Responses[1].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][16].Question == 'Annual combined income of youth and family:') otherwise "",
	"Q40_Youth_s_alcohol_use__c" : ((flowVars.inputData.QuestionResponses["Substance Abuse"][0].Responses[0].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][0].Responses[1].Response default "") ) 
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][0].Question == "Youth's alcohol use:") otherwise "",
	"Question_40_Additional_Answers__c" : (("Alcohol disrupting education;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][0].Responses map $.Response contains "Alcohol disrupting education" default "") otherwise "") ++
		("Alcohol causing family conflict;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][0].Responses map $.Response contains "Alcohol causing family conflict" default "") otherwise "") ++
		("Alcohol interfering with keeping pro-social friends;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][0].Responses map $.Response contains "Alcohol interfering with keeping pro-social friends" default "") otherwise "") ++ 
		("Alcohol causing health problems;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][0].Responses map $.Response contains "Alcohol causing health problems" default "") otherwise "") ++ 
		("Alcohol contributing to delinquent behavior;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][0].Responses map $.Response contains "Alcohol contributing to delinquent behavior" default "") otherwise "") ++ 
		("Youth needs increasing amounts of alcohol to achieve same level of intoxication or high;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][0].Responses map $.Response contains "Youth needs increasing amounts of alcohol to achieve same level of intoxication or high" default "") otherwise "") ++ 
		("Youth experiences withdrawal symptoms;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][0].Responses map $.Response contains "Youth experiences withdrawal symptoms" default "") otherwise "")),
	"Q41_Youth_s_drug_use__c" : ((flowVars.inputData.QuestionResponses["Substance Abuse"][1].Responses[0].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Responses[1].Response default "")) 
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Question == "Youth's drug use:") otherwise "",
	"Question_41_Additional_Answers__c" : ( ("Drugs disrupting education;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Responses map $.Response contains "Drugs disrupting education") otherwise "" default "" ) ++ 
		("Drugs causing family conflict;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Responses map $.Response contains "Drugs causing family conflict") otherwise "" default "" ) ++ 
		("Drugs interfering with keeping pro-social friends;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Responses map $.Response contains "Drugs interfering with keeping pro-social friends") otherwise "" default "" ) ++ 
		("Drugs causing health problems;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Responses map $.Response contains "Drugs causing health problems") otherwise "" default "" ) ++ 
		("Drugs contributing to delinquent behavior;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Responses map $.Response contains "Drugs contributing to delinquent behavior") otherwise "" default "" ) ++ 
		("Youth needs increasing amounts of drugs to achieve same level of intoxication or high;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Responses map $.Response contains "Youth needs increasing amounts of drugs to achieve same level of intoxication or high") otherwise "" default "" ) ++ 
		("Youth experiences withdrawal symptoms;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Responses map $.Response contains "Youth experiences withdrawal symptoms") otherwise "" default "" ) ) ,
	"Q42_Types_of_drugs_currently_used__c" : (("No current use of drugs;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses map $.Response contains "No current use of drugs") otherwise "" ) ++  
		("Currently using drugs" when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses map $.Response contains "Currently using drugs") otherwise "" ) ),
	"Question_42_Additional_Answers__c" : (("Marijuana/Hashish;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses map $.Response contains "Marijuana/Hashish") otherwise "" ) ++  
		("Inhalants (Glue/Gasoline);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses map $.Response contains "Inhalants (Glue/Gasoline)") otherwise "" ) ++ 
		("Cocaine (Crack/Rock);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses map $.Response contains "Cocaine (Crack/Rock)") otherwise "" ) ++  
		("Cocaine (Coke);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses map $.Response contains "Cocaine (Coke)") otherwise "" ) ++ 
		("Amphetamines (Meth/Uppers/Speed);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses map $.Response contains "Amphetamines (Meth/Uppers/Speed)") otherwise "" ) ++  
		("Ecstasy;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses map $.Response contains "Ecstasy") otherwise "" ) ++ 
		("Barbiturates (Tuinal/Seconal/Downers);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses map $.Response contains "Barbiturates (Tuinal/Seconal/Downers)") otherwise "" ) ++ 
		("Tranquilizers/Sedatives (Valium/Librium/Dalmane/Ketamine);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses map $.Response contains "Tranquilizers/Sedatives (Valium/Librium/Dalmane/Ketamine)") otherwise "" ) ++  
		("Hallucinogens (LSD/Acid/Mushrooms/GHB);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses map $.Response contains "Hallucinogens (LSD/Acid/Mushrooms/GHB)") otherwise "" ) ++  
		("Phencyclidine (PCP/Angel Dust);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses map $.Response contains "Phencyclidine (PCP/Angel Dust)") otherwise "" ) ++  
		("Heroin;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses map $.Response contains "Heroin") otherwise "" ) ++
		("Other opiates (Dilaudid/Demerol/Percodan/Codeine/Oxycontin);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses map $.Response contains "Other opiates (Dilaudid/Demerol/Percodan/Codeine/Oxycontin)") otherwise "" ) ++ 
		("OTCs (Cough Syrup, Sudafed, etc);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses map $.Response contains "OTCs (Cough Syrup, Sudafed, etc)") otherwise "" ) ++ 
		("Prescription medication(s) not prescribed to youth;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses map $.Response contains "Prescription medication(s) not prescribed to youth") otherwise "" ) ++  
		("Prescription medication(s) prescribed to youth;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses map $.Response contains "Prescription medication(s) prescribed to youth") otherwise "" ) ++  
		("Other drugs" when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses map $.Response contains "Other drugs") otherwise "" )), 
	"Question_42_Describe__c" : ((payload."Question_42_Describe__c"[0] default "") ++ ";" ++ (payload."Question_42_Describe__c"[1] default "") ++ ";" ++ (payload."Question_42_Describe__c"[2] default "")),
	"Q43_SA_treatment_program_participation__c" : flowVars.inputData.QuestionResponses["Substance Abuse"][3].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][3].Question == "Current drug/alcohol treatment program participation:") otherwise "",
	"Q44_History_of_youth_s_drug_use__c" : flowVars.inputData.QuestionResponses["Substance Abuse"][4].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][4].Question == "History of youth's drug use:") otherwise "",
	"Question_44_Additional_Answers__c" : (("Drugs disrupted education;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][4].Responses map $.Response contains "Drugs disrupted education") otherwise "") ++
		("Drugs caused family conflict;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][4].Responses map $.Response contains "Drugs caused family conflict") otherwise "") ++
		("Drugs interfered with keeping pro-social friends;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][4].Responses map $.Response contains "Drugs interfered with keeping pro-social friends") otherwise "") ++
		("Drugs caused health problems;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][4].Responses map $.Response contains "Drugs caused health problems") otherwise "") ++
		("Drugs contributed to delinquent behavior;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][4].Responses map $.Response contains "Drugs contributed to delinquent behavior") otherwise "") ++
		("Youth needed increasing amounts of drugs to achieve same level of intoxication or high;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][4].Responses map $.Response contains "Youth needed increasing amounts of drugs to achieve same level of intoxication or high") otherwise "") ++
		("Youth experienced withdrawal symptoms;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][4].Responses map $.Response contains "Youth experienced withdrawal symptoms") otherwise "") ) ,
	"Q45_Current_suicidal_ideation__c" : (("Does not currently have serious thoughts about suicide;" when (flowVars.inputData.QuestionResponses["Mental Health"][0].Responses map $.Response contains ("Does not currently have serious thoughts about suicide") ) otherwise "") ++ 
		 ("Feels life is not worth living - no hope for future;" when (flowVars.inputData.QuestionResponses["Mental Health"][0].Responses map $.Response contains ("Feels life is not worth living - no hope for future")) otherwise "") ++  
		 ("Has serious thoughts about suicide;" when (flowVars.inputData.QuestionResponses["Mental Health"][0].Responses map $.Response contains ("Has serious thoughts about suicide")) otherwise "" ) ++ 
		 ("Knew someone well who has committed suicide;" when (flowVars.inputData.QuestionResponses["Mental Health"][0].Responses map $.Response contains ("Knew someone well who has committed suicide")) otherwise "") ++  
		 ("Has made a plan to commit suicide;" when (flowVars.inputData.QuestionResponses["Mental Health"][0].Responses map $.Response contains ("Has made a plan to commit suicide")) otherwise "") ++
		 ("Engages in self-mutilating behavior;" when (flowVars.inputData.QuestionResponses["Mental Health"][0].Responses map $.Response contains ("Engages in self-mutilating behavior")) otherwise "") ++  		 
		 ("Has recently attempted to commit suicide." when (flowVars.inputData.QuestionResponses["Mental Health"][0].Responses map $.Response contains ("Has recently attempted to commit suicide.")) otherwise "" )) ,
	"Question_45_Known_Person_Suicide_Date__c" : (((payload.Question_45_Known_Person_Suicide_Date__c[0] ++ |05:00|)  as :date {format : "yyyy-MM-dd"} ) when (flowVars.inputData.QuestionResponses["Mental Health"][0].Responses map $.Response contains ("Knew someone well who has committed suicide")) otherwise "") ,
	"Question_45_Attempted_Suicide_Date__c" : (((payload.Question_45_Attempted_Suicide_Date__c[0] ++ |05:00|) as :date {format : "yyyy-MM-dd"} ) when (flowVars.inputData.QuestionResponses["Mental Health"][0].Responses map $.Response contains ("Has recently attempted to commit suicide.")) otherwise "") ,
	"Question_45_Additional_Answers__c" : (payload.Question_45_Additional_Answers__c[0] default "") ++ ";" ++ (payload.Question_45_Additional_Answers__c[1] default "") ++ ";" ++ (payload.Question_45_Additional_Answers__c[2] default ""),
	//"Question_45_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Mental Health"][0].Responses filter (($.Label == "Describe:") or ($.Label == "Who and how:") or ($.Label == "Date:") or ($.Label == "Date of last attempt:") ) map $.Response,
	"Q46_Currently_diagnosed_with_ADD_ADHD__c" : flowVars.inputData.QuestionResponses["Mental Health"][1].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Mental Health"][1].Question == "Currently diagnosed with ADD/ADHD:") otherwise "",
	"Question_46_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Mental Health"][1].Responses[1].Response 
	when (flowVars.inputData.QuestionResponses["Mental Health"][1].Responses[1].Response != null) otherwise "",
	"Q47_MH_counseling_currently_recommended__c" : ((flowVars.inputData.QuestionResponses["Mental Health"][2].Responses[0].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][2].Responses[1].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][2].Responses[2].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][2].Responses[3].Response default "") ) 
	when (flowVars.inputData.QuestionResponses["Mental Health"][2].Question == "Mental health counseling currently recommended (excluding ADD/ADHD treatment):") otherwise "",
	"Question_47_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Mental Health"][2].Responses[1].Response 
	when (flowVars.inputData.QuestionResponses["Mental Health"][2].Responses[1].Response != null) otherwise "",
	"Q48_MH_medication_currently_prescribed__c" : flowVars.inputData.QuestionResponses["Mental Health"][3].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Mental Health"][3].Question == "Mental health medication currently prescribed (excluding ADD/ADHD medication):") otherwise "",
	"Q49_Currently_have_health_insurance__c" : flowVars.inputData.QuestionResponses["Mental Health"][4].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Mental Health"][4].Question == "Does youth currently have health insurance?") otherwise "",
	"Question_49_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Mental Health"][4].Responses[1].Response 
	when (flowVars.inputData.QuestionResponses["Mental Health"][4].Responses[1].Response != null) otherwise "",
	"Q50_History_of_violence_physical_abuse__c" : ((flowVars.inputData.QuestionResponses["Mental Health"][5].Responses[0].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][5].Responses[1].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][5].Responses[2].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][5].Responses[3].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][5].Responses[4].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][5].Responses[5].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][5].Responses[6].Response default "") ) 
	when (flowVars.inputData.QuestionResponses["Mental Health"][5].Question == "History of violence/physical abuse:") otherwise "",
	"Q51_History_of_witnessing_violence__c" : ((flowVars.inputData.QuestionResponses["Mental Health"][6].Responses[0].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][6].Responses[1].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][6].Responses[2].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][6].Responses[3].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][6].Responses[4].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][6].Responses[5].Response default "")) 
	when (flowVars.inputData.QuestionResponses["Mental Health"][6].Question == "History of witnessing violence:") otherwise "",
	"Q52_History_of_sexual_abuse_rape__c" : ((flowVars.inputData.QuestionResponses["Mental Health"][7].Responses[0].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][7].Responses[1].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][7].Responses[2].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][7].Responses[3].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][7].Responses[4].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][7].Responses[5].Response default ""))  
	when (flowVars.inputData.QuestionResponses["Mental Health"][7].Question == "History of sexual abuse/rape:") otherwise "",
	"Q53_Main_emotion_during_delinquent_acts__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][0].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][0].Question == "Primary emotion when committing acts of delinquency:") otherwise "",
	"Q54_Main_reason_for_delinquent_acts_6mos__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][1].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][1].Question == "Primary purpose for committing acts of delinquency within the last 6 months:") otherwise "",
	"Question_54_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][1].Responses[1].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][1].Responses[1].Response != null) otherwise "",
	"Q55_Level_of_optimism__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][2].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][2].Question == "Level of optimism:") otherwise "",
	"Q56_Level_of_impulsiveness__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][3].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][3].Question == "Level of impulsiveness:") otherwise "",
	"Q57_Control_of_anti_social_behavior__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][4].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][4].Question == "Belief in control over anti-social behavior:") otherwise "",
	"Q58_Empathy_for_victim_of_delinquency__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][5].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][5].Question == "Empathy, remorse, sympathy, or feelings for the victim(s) of delinquent behavior:") otherwise "",
	"Q59_Respect_for_property_of_others__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][6].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][6].Question == "Respect for property of others:") otherwise "",
	"Q60_Respect_for_authority_figures__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][7].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][7].Question == "Respect for authority figures:") otherwise "",
	"Q61_Anti_social_behavior_responsibility__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][8].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][8].Question == "Accepts responsibility for anti-social behavior:") otherwise "",	
	"Q62_Belief_in_meeting_conditions__c" : ((flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][9].Responses[0].Response default "")  ) 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][9].Question == "Youth's belief in successfully meeting conditions of court supervision:") otherwise "",
	"Question_62_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][9].Responses[1].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][9].Responses[1].Response != null) otherwise "",
	"Q63_Tolerance_for_frustration__c" : flowVars.inputData.QuestionResponses["Aggression"][0].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Aggression"][0].Question == "Tolerance for frustration:") otherwise "",
	"Q64_Perception_of_intentions_of_others__c" : flowVars.inputData.QuestionResponses["Aggression"][1].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Aggression"][1].Question == "Perception of intentions of others (in common, non-confrontational settings):") otherwise "",
	"Q65_Belief_in_verbal_aggression__c" : flowVars.inputData.QuestionResponses["Aggression"][2].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Aggression"][2].Question == "Belief in yelling and verbal aggression to resolve a disagreement or conflict:") otherwise "",
	"Q66_Belief_in_physical_aggression__c" : flowVars.inputData.QuestionResponses["Aggression"][3].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Aggression"][3].Question == "Belief in fighting and physical aggression to resolve a disagreement or conflict:") otherwise ""
}
