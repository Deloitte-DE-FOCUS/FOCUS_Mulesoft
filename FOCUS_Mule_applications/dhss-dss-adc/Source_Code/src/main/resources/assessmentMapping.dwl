%dw 1.0
%output application/java
%function getDSTAdjustedDatetime(standarddatetime) (standarddatetime - |PT4H| )  when  (isDateInDST(((standarddatetime - |PT4H|) ) as :date,"US/Eastern") == true) otherwise (standarddatetime - |PT5H|)
---
{
	"Id": payload.AssessmentExtensionSFId,
	"Vantage_Assessment_Created_Date__c": (flowVars.inputData.CreatedDate as :datetime + |PT4H|) when flowVars.inputData.CreatedDate != null otherwise null,
	// as :string {format:"yyyy-MM-dd'T'HH:mm:ss.SSS"},
	"Assessment_Title__c": flowVars.inputData.AssessmentTitle,
	"Completed_Date_ADC__c": (flowVars.inputData.DateCompleted as :datetime + |PT4H|) when flowVars.inputData.DateCompleted != null otherwise null,
	"Assessment_Type__c": flowVars.inputData.AssessmentType,
	"VantagePoint_Assessment_ID__c": flowVars.inputData.OffenderAssessmentID as :string,
	"Completed_By__c": flowVars.inputData.CompletedBy,
	"Overall_Level_Of_Risk_to_Re_Offend_Score__c" : flowVars.inputData.Properties.OverallRiskLevel when (flowVars.inputData.Properties.OverallRiskLevel != null and flowVars.inputData.Properties.OverallRiskLevel != "") otherwise "",
	"Criminogenic_Need_1__c": flowVars.inputData.Properties.CriminogenicNeed1 when (flowVars.inputData.Properties.CriminogenicNeed1 != null and flowVars.inputData.Properties.CriminogenicNeed1 != "") otherwise "",
	"Criminogenic_Need_2__c": flowVars.inputData.Properties.CriminogenicNeed2 when (flowVars.inputData.Properties.CriminogenicNeed2 != null and flowVars.inputData.Properties.CriminogenicNeed2 != "") otherwise "",
	"Protective_Factor_1__c" : flowVars.inputData.Properties.ProtectiveFactor1 when (flowVars.inputData.Properties.ProtectiveFactor1 != null and flowVars.inputData.Properties.ProtectiveFactor1 != "") otherwise "",
	"Protective_Factor_2__c" : flowVars.inputData.Properties.ProtectiveFactor2 when (flowVars.inputData.Properties.ProtectiveFactor2 != null and flowVars.inputData.Properties.ProtectiveFactor2 != "") otherwise "",
	"Vantage_Assessment_Status__c": "Completed",
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
	"Q9_Youth_s_current_setting__c" : flowVars.inputData.QuestionResponses["Residential Placement & Capias"][0].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Residential Placement & Capias"][0].Question == "Youth's current setting:") otherwise "",
	"Question_9_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Residential Placement & Capias"][0].Responses[1].Response 
	when (flowVars.inputData.QuestionResponses["Residential Placement & Capias"][0].Responses[1].Response != null) otherwise "",
	"Q10_Pre_adjudication_stays_in_RAD__c" : flowVars.inputData.QuestionResponses["Residential Placement & Capias"][1].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Residential Placement & Capias"][1].Question == "Pre-adjudication stays in Residential Alternative to Detention:") otherwise "",
	"Q11_Confinements_in_detention_48_hours__c" : flowVars.inputData.QuestionResponses["Residential Placement & Capias"][2].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Residential Placement & Capias"][2].Question == "Confinements in detention where youth was held for at least 48 hours:") otherwise "",
	"Q12_Residential_placement_1_day_or_more__c" : flowVars.inputData.QuestionResponses["Residential Placement & Capias"][3].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Residential Placement & Capias"][3].Question == "Commitment orders where youth served at least one day confined under residential commitment:") otherwise "",
	"Q13_Capias_for_FTA_in_court__c" : flowVars.inputData.QuestionResponses["Residential Placement & Capias"][4].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Residential Placement & Capias"][4].Question == "Capias for failure-to-appear in court:") otherwise "",
	"Q14_Capias_for_absconding_from_probation__c" : flowVars.inputData.QuestionResponses["Residential Placement & Capias"][5].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Residential Placement & Capias"][5].Question == "Capias for absconding from supervision:") otherwise "",
	"Q15_School_status_last_6_months__c" : flowVars.inputData.QuestionResponses["Education"][0].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][0].Question == "Youth has been enrolled in school during the last 6 months in the community, regardless of attendance:") otherwise "",
	"Q16_Current_school_enrollment_status__c" : flowVars.inputData.QuestionResponses["Education"][1].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][1].Question == "Youth's current school enrollment status either in the community or just prior to residential admission (regardless of attendance):") otherwise "",
	"Q17_School_type_youth_is_enrolled__c" : flowVars.inputData.QuestionResponses["Education"][2].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][2].Question == "Type of school in which youth is enrolled:") otherwise "",
	"Question_17_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Education"][2].Responses[1].Response,
	"Question_17_Describe_School_Type__c" : (payload."Question_17_Describe_School_Type__c"[0] default ""),
	"Q18_Conduct_in_the_most_recent_term__c" : flowVars.inputData.QuestionResponses["Education"][3].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][3].Question == "Youth's conduct in the most recent term:") otherwise "",
	"Q19_Suspensions_expulsions_1st_grade_on__c" : (("No history of school suspension or expulsion;" when (flowVars.inputData.QuestionResponses["Education"][4].Responses map $.Response contains "No history of school suspension or expulsion" ) otherwise "" default "") ++ 
	("In school suspension(s);" when (flowVars.inputData.QuestionResponses["Education"][4].Responses map $.Response contains "In school suspension(s)" ) otherwise "" default "") ++ 
	("Out of school suspension(s);" when (flowVars.inputData.QuestionResponses["Education"][4].Responses map $.Response contains "Out of school suspension(s)" ) otherwise "" default "") ++ 
	("Expulsions;" when (flowVars.inputData.QuestionResponses["Education"][4].Responses map $.Response contains "Expulsions" ) otherwise "" default "") ),
	"Question_19_Additional_Answers__c" : ((payload.Question_19_Additional_Answers__c[0] default "") ++ ";" ++ (payload.Question_19_Additional_Answers__c[1] default "") ++ ";" ++ (payload.Question_19_Additional_Answers__c[2] default "") ++ ";" ++ (payload.Question_19_Additional_Answers__c[3] default "")),
	"Q20_Suspensions_expulsions_recent_term__c" : (("No school suspensions or expulsions in the most recent term;" when (flowVars.inputData.QuestionResponses["Education"][5].Responses map $.Response contains "No school suspensions or expulsions in the most recent term")  otherwise "") ++ ("In school suspension(s);" when (flowVars.inputData.QuestionResponses["Education"][5].Responses map $.Response contains "In school suspension(s)") otherwise "") ++ 
	("Out of school suspension(s);" when (flowVars.inputData.QuestionResponses["Education"][5].Responses map $.Response contains "Out of school suspension(s)") otherwise "") ++ 
	("Expulsion(s);" when (flowVars.inputData.QuestionResponses["Education"][5].Responses map $.Response contains "Expulsion(s)") otherwise "")),
	"Question_20_Additional_Answers__c" : ((payload.Question_20_Additional_Answers__c[0] default "") ++ ";" ++ (payload.Question_20_Additional_Answers__c[1] default "") ++ ";" ++ (payload.Question_20_Additional_Answers__c[2] default "")),
	"Q21_Youth_is_a_special_education_student__c" : (("No special education need;" when (flowVars.inputData.QuestionResponses["Education"][6].Responses map $.Response contains "No special education need") otherwise "") ++ 
		("Learning impairment;" when (flowVars.inputData.QuestionResponses["Education"][6].Responses map $.Response contains "Learning impairment") otherwise "") ++
		("Cognitive Impairment;" when (flowVars.inputData.QuestionResponses["Education"][6].Responses map $.Response contains "Cognitive Impairment") otherwise "") ++ 
		("Behavioral need;" when (flowVars.inputData.QuestionResponses["Education"][6].Responses map $.Response contains "Behavioral need") otherwise "") ++ 
		("ADHD/ADD;" when (flowVars.inputData.QuestionResponses["Education"][6].Responses map $.Response contains "ADHD/ADD") otherwise "") ++ 
		("Other health impairment;" when (flowVars.inputData.QuestionResponses["Education"][6].Responses map $.Response contains "Other health impairment") otherwise "") ++ 
		("Has an active IEP" when (flowVars.inputData.QuestionResponses["Education"][6].Responses map $.Response contains "Has an active IEP") otherwise "")),
	"Question_21_Additional_Answers__c" : ( payload."Question_21_Additional_Answers__c"[0] default "") ,
	"Question_21_Date_of_Last_IEP__c" : (((payload."Question_21_Date_of_Last_IEP__c"[0] ++ |00:00|) as :date {format : "yyyy-MM-dd"}) default ""),//as :date {format : "yyyyMMdd"} as :string {format: "yyyy-MM-dd"}
	"Q22_Youth_belief_in_getting_an_education__c" : flowVars.inputData.QuestionResponses["Education"][7].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][7].Question == "Youth believes there is value in getting an education:") otherwise "",
	"Q23_Youth_belief_school_is_encouraging__c" : flowVars.inputData.QuestionResponses["Education"][8].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][8].Question == "Youth believes school provides an encouraging environment for him or her:") otherwise "",
	"Q24_Staff_that_youth_likes__c" : flowVars.inputData.QuestionResponses["Education"][9].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][9].Question == "Teachers, instructors, staff, or coaches whom the youth likes, or with whom the youth feels comfortable talking:") otherwise "",
	"Q25_School_activities_during_recent_term__c" : flowVars.inputData.QuestionResponses["Education"][10].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][10].Question == "Youth's involvement in school activities during most recent term:") otherwise "",
	"Q26_Attendance_in_the_most_recent_term__c" : flowVars.inputData.QuestionResponses["Education"][11].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][11].Question == "Youth's attendance in the most recent term:") otherwise "",
	"Q27_Youth_grades_in_recent_school_term__c" : flowVars.inputData.QuestionResponses["Education"][12].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][12].Question == "Youth's academic performance in the most recent school term:") otherwise "",
	"Q28_Will_youth_stay_in_school_graduate__c" : flowVars.inputData.QuestionResponses["Education"][13].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Education"][13].Question == "Interviewer's assessment of likelihood the youth will stay in and graduate from high school or an equivalent vocational school:") otherwise "",
	"Q29_Interest_in_structured_activities__c" : flowVars.inputData.QuestionResponses["Use of Free Time"][0].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Use of Free Time"][0].Question == "Current interest and involvement in structured non-academic and/or recreational activities:") otherwise "",
	"Q30_History_of_structured_activities__c" : flowVars.inputData.QuestionResponses["Use of Free Time"][1].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Use of Free Time"][1].Question == "History of structured non-academic and/or recreational activities:") otherwise "",
	"Q31_Interest_in_unstructured_activities__c" : flowVars.inputData.QuestionResponses["Use of Free Time"][2].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Use of Free Time"][2].Question == "Current interest and involvement in unstructured non-academic and/or recreational activities that positively occupy the youth's time:") otherwise "",
	"Q32_Residential_Unstructured_time_spent__c" : flowVars.inputData.QuestionResponses["Use of Free Time"][3].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Use of Free Time"][3].Question == "During current residential stay, how has the youth used unstructured non-academic and/or leisure time?") otherwise "",
	"Q33_Current_employment_status__c" : flowVars.inputData.QuestionResponses["Employment"][0].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Employment"][0].Question == "Current employment status:") otherwise "",
	"Question_33_Additional_Answers__c" : (("Current employment is going well;" when (flowVars.inputData.QuestionResponses["Employment"][0].Responses contains "Current employment is going well") otherwise "") default "" ++ 
	("Having problems with current employment;" when (flowVars.inputData.QuestionResponses["Employment"][0].Responses contains "Having problems with current employment") otherwise "") ++ 
	("Too young for employment consideration (generally 13 years old or younger);" when (flowVars.inputData.QuestionResponses["Employment"][0].Responses map $.Response contains "Too young for employment consideration (generally 13 years old or younger)") otherwise "") ++
	("Employment terminated since last assessment;" when (flowVars.inputData.QuestionResponses["Employment"][0].Responses map $.Response contains "Employment terminated since last assessment") otherwise "") ++
	("14-15 years old and has never been employed;" when (flowVars.inputData.QuestionResponses["Employment"][0].Responses map $.Response contains "14-15 years old and has never been employed") otherwise "") ++
	("16 years old or older and has never been employed;" when (flowVars.inputData.QuestionResponses["Employment"][0].Responses map $.Response contains "16 years old or older and has never been employed") otherwise "")),
	"Q34_Behavior_at_employment__c" : flowVars.inputData.QuestionResponses["Employment"][1].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Employment"][1].Question == "Behavior at employment:") otherwise "",
	"Q35_Current_interest_in_employment__c" : flowVars.inputData.QuestionResponses["Employment"][2].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Employment"][2].Question == "Current interest in employment:") otherwise "",
	"Q36_History_of_employment_performance__c" : flowVars.inputData.QuestionResponses["Employment"][3].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Employment"][3].Question == "History of employment performance:") otherwise "",
	"Question_36_Additional_Answers__c" : (("Has received some form of positive reinforcement in job;" when (flowVars.inputData.QuestionResponses["Employment"][3].Responses map $.Response contains "Has received some form of positive reinforcement in job") otherwise "") ++ 
	("Poor performance;" when (flowVars.inputData.QuestionResponses["Employment"][3].Responses map $.Response contains "Poor performance") otherwise "") ++ 
	("Poor attendance;" when (flowVars.inputData.QuestionResponses["Employment"][3].Responses map $.Response contains "Poor attendance") otherwise "") ++ 
	("Interpersonal problems with employer or co-workers;" when (flowVars.inputData.QuestionResponses["Employment"][3].Responses map $.Response contains "Interpersonal problems with employer or co-workers") otherwise "") ++ 
	("Anti-social/delinquent behavior on the job;" when (flowVars.inputData.QuestionResponses["Employment"][3].Responses map $.Response contains "Anti-social/delinquent behavior on the job") otherwise "") ++ 
	("Interpersonal problems with employer or co-workers;" when (flowVars.inputData.QuestionResponses["Employment"][3].Responses map $.Response contains "Interpersonal problems with employer or co-workers") otherwise "") ++
	("Issues unrelated to employment;" when (flowVars.inputData.QuestionResponses["Employment"][3].Responses map $.Response contains "Issues unrelated to employment") otherwise "") ++
	("New job opportunity;" when (flowVars.inputData.QuestionResponses["Employment"][3].Responses map $.Response contains "New job opportunity") otherwise "") ) ,
	"Q37_Positive_relationship_w_coworkers__c" : flowVars.inputData.QuestionResponses["Employment"][4].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Employment"][4].Question == "Current positive personal relationship(s) with employer(s)/coworker(s):") otherwise "",
	"Q38_Understands_requirements_for_a_job__c" : flowVars.inputData.QuestionResponses["Employment"][5].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Employment"][5].Question == "Understanding of what is required to maintain a job:") otherwise "",
	"Q39_Friends_Companions_youth_spends_time__c" : ((flowVars.inputData.QuestionResponses["Relationships"][0].Responses[0].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Relationships"][0].Responses[1].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Relationships"][0].Responses[2].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Relationships"][0].Responses[3].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Relationships"][0].Responses[4].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Relationships"][0].Responses[5].Response default "")) 
	when (flowVars.inputData.QuestionResponses["Relationships"][0].Question == "Current friends/companions with whom youth actually spends time:") otherwise "",
	"Q40_Peers_youth_associates_with__c" : (("Community;" when (flowVars.inputData.QuestionResponses["Relationships"][1].Responses map $.Response contains "Community") otherwise "") ++
		("Positive peers;" when (flowVars.inputData.QuestionResponses["Relationships"][1].Responses map $.Response contains "Positive peers") otherwise "") ++
		("No consistent friends or companions;" when (flowVars.inputData.QuestionResponses["Relationships"][1].Responses map $.Response contains "No consistent friends or companions") otherwise "") ++
		("Negative peers;" when (flowVars.inputData.QuestionResponses["Relationships"][1].Responses map $.Response contains "Negative peers") otherwise "") ++
		("Is suspected gang member;" when (flowVars.inputData.QuestionResponses["Relationships"][1].Responses map $.Response contains "Is suspected gang member") otherwise "") ++
		("Is a validated gang member;" when (flowVars.inputData.QuestionResponses["Relationships"][1].Responses map $.Response contains "Is a validated gang member") otherwise "") ),
	"Q41_Currently_in_a_romantic_relationship__c" : flowVars.inputData.QuestionResponses["Relationships"][2].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Relationships"][2].Question == 'Currently in a "romantic" or intimate relationship:') otherwise "",
	"Question_41_Additional_Answers__c" : ((flowVars.inputData.QuestionResponses["Relationships"][2].Responses[1].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Relationships"][2].Responses[2].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Relationships"][2].Responses[3].Response default ""))
	when (flowVars.inputData.QuestionResponses["Relationships"][2].Responses[1].Response != null) otherwise "",
	"Q42_Positive_non_relative_relationship__c" : flowVars.inputData.QuestionResponses["Relationships"][3].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Relationships"][3].Question == "Current positive adult non-family relationships not connected to school or employment:") otherwise "",
	"Q43_Current_pro_social_community_ties__c" : flowVars.inputData.QuestionResponses["Relationships"][4].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Relationships"][4].Question == "Current pro-social community ties:") otherwise "",
	"Q44_Admires_emulates_antisocial_peers__c" : flowVars.inputData.QuestionResponses["Relationships"][5].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Relationships"][5].Question == "Currently admires/emulates anti-social peers:") otherwise "",
	"Q45_Anti_social_peer_influence__c" : flowVars.inputData.QuestionResponses["Relationships"][6].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Relationships"][6].Question == "Current resistance to anti-social peer influence:") otherwise "",
	"Q46_Youth_living_under_adult_supervision__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][0].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][0].Question == 'Youth living under any "adult supervision":') otherwise "",
	"Q47_Currently_kicked_out_of_home_runaway__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][1].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][1].Question == 'Youth is currently kicked out of home or is a runaway:') otherwise "",
	"Q48_History_kicked_out_of_home_runaway__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][2].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][2].Question == 'History of running away or getting kicked out of home:') otherwise "",
	"Q49_History_of_being_a_victim_of_neglect__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][3].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][3].Question == 'History of being a victim of neglect') otherwise "",
	"Q50_History_of_DFS_involvement__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][4].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][4].Question == 'History of DFS involvement:') otherwise "",
	"Question_50_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][4].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][4].Responses[1].Response != null) otherwise "",
	"Q51_Youth_is_a_parent__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][5].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][5].Question == 'Youth is a parent:') otherwise "",
	"Question_51_Additional_Answers__c" : ((flowVars.inputData.QuestionResponses["Family & Social Support"][5].Responses[0].Response default "" ) ++ ";" ++ (flowVars.inputData.QuestionResponses["Family & Social Support"][5].Responses[1].Response default "" ) ++ ";" ++ (flowVars.inputData.QuestionResponses["Family & Social Support"][5].Responses[2].Response default "" ) ++ ";" ++ (flowVars.inputData.QuestionResponses["Family & Social Support"][5].Responses[3].Response default "" )) 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][5].Responses[1].Response != null) otherwise "",
	"Q52_Who_is_youth_living_with__c" : (("Living alone;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Living alone") otherwise "") ++ 
		("Parent(s);" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Parent(s)") otherwise "") ++ 
		("Other relative(s);" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Other relative(s)") otherwise "") ++ 
		("Non-relative(s);" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Non-relative(s)") otherwise "") ++ 
		("Foster/group home;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Foster/group home") otherwise "") ++ 
		("Transient (street/incarcerated)" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Transient (street/incarcerated)") otherwise ""))  ,
	"Question_52_Additional_Answers__c" : (("Biological/Non-biological mother;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Biological/Non-biological mother") otherwise "") ++
		("Biological/Non-biological father;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Biological/Non-biological father") otherwise "") ++ 
		("Long-term parental partner(s);" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Long-term parental partner(s)") otherwise "") ++  
		("Short-term parental partner(s);" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Short-term parental partner(s)") otherwise "") ++ 
		("Grandparent(s);" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Grandparent(s)") otherwise "") ++ 
		("Older sibling(s);" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Older sibling(s)") otherwise "") ++ 
		("Younger sibling(s);" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Younger sibling(s)") otherwise "") ++ 
		("Youth's child;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Youth's child") otherwise "") ++ 
		("Other relative;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Other relative") otherwise "") ++  
		("Family friend;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Family friend") otherwise "") ++ 
		("Parent's roommate;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Parent's roommate") otherwise "") ++ 
		("Youth's romantic partner;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Youth's romantic partner") otherwise "") ++ 
		("Youth's friend;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Youth's friend") otherwise "") ++ 
		("Other non-relative;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][6].Responses map $.Response contains "Other non-relative") otherwise "")) ,
	"Question_52_Relative_Name__c" : (payload."Question_52_Relative_Name__c"[0] default ""),   
	"Question_52_Non_Relative_Name__c" : (payload."Question_52_Non_Relative_Name__c"[0] default ""),
	"Q53_Problem_history_of_parents__c" : ((flowVars.inputData.QuestionResponses["Family & Social Support"][7].Responses[0].Response default "") ++ ";"  ++ (flowVars.inputData.QuestionResponses["Family & Social Support"][7].Responses[1].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Family & Social Support"][7].Responses[2].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Family & Social Support"][7].Responses[3].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Family & Social Support"][7].Responses[4].Response default "")) 
		when (flowVars.inputData.QuestionResponses["Family & Social Support"][7].Question == 'Problem history of adults who are currently involved with the household:') otherwise "",
	"Q54_Problem_history_of_siblings__c" : ((flowVars.inputData.QuestionResponses["Family & Social Support"][8].Responses[0].Response default "" ) ++ ";" ++ (flowVars.inputData.QuestionResponses["Family & Social Support"][8].Responses[1].Response default "" ) ++ ";" ++ (flowVars.inputData.QuestionResponses["Family & Social Support"][8].Responses[2].Response default "" ) ++ ";" ++ (flowVars.inputData.QuestionResponses["Family & Social Support"][8].Responses[3].Response default "" ) ++ ";" ++ (flowVars.inputData.QuestionResponses["Family & Social Support"][8].Responses[4].Response default "" ) )  
		when (flowVars.inputData.QuestionResponses["Family & Social Support"][8].Question == 'Problem history of siblings who are currently involved with the household:') otherwise "",
	"Q55_Imprisonment_history_household__c" : (("No jail/imprisonment history in family;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][9].Responses map $.Response contains "No jail/imprisonment history in family") otherwise "") ++  
		("Mother/female caretaker;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][9].Responses map $.Response contains "Mother/female caretaker") otherwise "") ++  
		("Father/male caretaker;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][9].Responses map $.Response contains "Father/male caretaker") otherwise "") ++ 
		("Older sibling;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][9].Responses map $.Response contains "Older sibling") otherwise "") ++ 
		("Younger sibling;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][9].Responses map $.Response contains "Younger sibling") otherwise "") ++ 
		("Other member" when (flowVars.inputData.QuestionResponses["Family & Social Support"][9].Responses map $.Response contains "Other member") otherwise "")),
	"Question_55_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][9].Responses[-1].Response 
		when (flowVars.inputData.QuestionResponses["Family & Social Support"][9].Responses map $.Response contains "Other member") otherwise "",
	"Q56_Imprisonment_history_household_3mos__c" : (("No jail/imprisonment history in family;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][10].Responses map $.Response contains "No jail/imprisonment history in family") otherwise "") ++  
		("Mother/female caretaker;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][10].Responses map $.Response contains "Mother/female caretaker") otherwise "") ++ 
		("Father/male caretaker;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][10].Responses map $.Response contains "Father/male caretaker") otherwise "") ++ 
		("Older sibling;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][10].Responses map $.Response contains "Older sibling") otherwise "") ++ 
		("Younger sibling;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][10].Responses map $.Response contains "Younger sibling") otherwise "") ++ 
		("Other member" when (flowVars.inputData.QuestionResponses["Family & Social Support"][10].Responses map $.Response contains "Other member") otherwise "")) ,
	"Question_56_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][10].Responses[-1].Response 
		when (flowVars.inputData.QuestionResponses["Family & Social Support"][10].Responses map $.Response contains "Other member") otherwise "",
	"Q57_Parental_supervision__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][11].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][11].Question == 'Parental supervision:') otherwise "",
	"Q58_Parental_authority_and_control__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][12].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][12].Question == 'Parental authority and control:') otherwise "",
	"Q59_Consistent_appropriate_punishment__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][13].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][13].Question == 'Consistent appropriate punishment for bad behavior:') otherwise "",
	"Q60_Consistent_appropriate_rewards__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][14].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][14].Question == 'Consistent appropriate rewards for good behavior:') otherwise "",
	"Q61_Parent_feelings_on_youth_delinquency__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][15].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][15].Question == "Parental characterization of youth's anti-social behavior:") otherwise "",
	"Q62_Family_willingness_to_support_youth__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][16].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][16].Question == "Family's willingness to help support youth:") otherwise "",
	"Q63_Family_with_whom_youth_feels_close__c" : (("Feels close to mother/female caretaker;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][17].Responses map $.Response contains "Feels close to mother/female caretaker") otherwise "") ++ 
		("Feels close to father/male caretaker;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][17].Responses map $.Response contains "Feels close to father/male caretaker") otherwise "") ++ 
 		("Feels close to male sibling;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][17].Responses map $.Response contains "Feels close to male sibling") otherwise "") ++ 
 		("Feels close to female sibling;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][17].Responses map $.Response contains "Feels close to female sibling") otherwise "") ++
 		("Feels close to extended family;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][17].Responses map $.Response contains "Feels close to extended family") otherwise "") ++ 
 		("Does not feel close to any family member;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][17].Responses map $.Response contains "Does not feel close to any family member") otherwise "") ++ 
 		("Other" when (flowVars.inputData.QuestionResponses["Family & Social Support"][17].Responses map $.Response contains "Other") otherwise "")),
	"Question_63_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][17].Responses[-1].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][17].Responses map $.Response contains "Other") otherwise "",
	"Q64_Level_of_conflict_between_family__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][18].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][18].Question == 'Level of conflict between parents, between youth and parents, or among siblings:') otherwise "",
	"Question_64_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][18].Responses[1].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][18].Responses[1].Response != null ) otherwise "",
	"Q65_Support_network_for_family__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][19].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][19].Question == 'Support network for family:') otherwise "",
	"Q66_Annual_income_of_youth_family__c" : (("Number of persons in household;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][20].Responses map $.Response contains "Number of persons in household") otherwise "") ++ 
	("Annual income;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][20].Responses map $.Response contains "Annual income") otherwise "") ++ 
	("Up to poverty line x 4;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][20].Responses map $.Response contains "Up to poverty line x 4") otherwise "") ++ 
	("Up to poverty line x 3;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][20].Responses map $.Response contains "Up to poverty line x 3") otherwise "") ++ 
	("Up to poverty line x 2;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][20].Responses map $.Response contains "Up to poverty line x 2") otherwise "") ++ 
	("Below poverty line;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][20].Responses map $.Response contains "Below poverty line") otherwise "") ++ 
	("Unknown;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][20].Responses map $.Response contains "Unknown") otherwise "")),
	"Question_66_Additional_Answers__c" : ((payload.Question_66_Additional_Answers__c[0] default "")), 
	"Question_66_Annual_Income__c" : ((payload.Question_66_Additional_Answers__c[1] default "")),
	"Q67_Current_contact_w_caretakers__c" : (("Community;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][21].Responses map $.Response contains "Community") otherwise "") ++ 
	("Minimal;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][21].Responses map $.Response contains "Minimal") otherwise "") ++  
	("Occasional;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][21].Responses map $.Response contains "Occasional") otherwise "") ++
	("Frequent" when (flowVars.inputData.QuestionResponses["Family & Social Support"][21].Responses map $.Response contains "Frequent") otherwise "") ),
	"Question_67_Additional_Answers__c" : (("Youth has no relationship with parents/caretakers;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][21].Responses map $.Response contains "Youth has no relationship with parents/caretakers") otherwise "") ++
		("Youth has relationship with parents/caretakers, but no contact while in residential setting;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][21].Responses map $.Response contains "Youth has relationship with parents/caretakers, but no contact while in residential setting") otherwise "") ++ 
		("Phone;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][21].Responses map $.Response contains "Phone") otherwise "") ++ 
		("Youth has no relationship with parents/caretakers;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][21].Responses map $.Response contains "Youth has no relationship with parents/caretakers" ) otherwise "") ++ 
		("Letters;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][21].Responses map $.Response contains "Letters") otherwise "") ++
		("Visits;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][21].Responses map $.Response contains "Visits") otherwise "") ++
		("Other;" when (flowVars.inputData.QuestionResponses["Family & Social Support"][21].Responses map $.Response contains "Other") otherwise "")), 
	"Question_67_Describe__c" : (payload."Question_67_Describe__c"[0] default "") ,
	"Q68_Parent_feelings_on_youth_s_progress__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][22].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][22].Question == "Parent/caretaker's characterization of youth's progress in commitment program:") otherwise "",
	"Q69_Status_of_family_goals__c" : flowVars.inputData.QuestionResponses["Family & Social Support"][23].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Family & Social Support"][23].Question == 'Status of family goals:') otherwise "",
	"Q70_Youth_s_alcohol_use__c" : flowVars.inputData.QuestionResponses["Substance Abuse"][0].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][0].Question == "Youth's alcohol use:") otherwise "",
	"Question_70_Additional_Answers__c" : ((flowVars.inputData.QuestionResponses["Substance Abuse"][0].Responses[1].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][0].Responses[2].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][0].Responses[3].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][0].Responses[4].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][0].Responses[5].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][0].Responses[6].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][0].Responses[7].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][0].Responses[8].Response default ""))
	when ( flowVars.inputData.QuestionResponses["Substance Abuse"][0].Responses[1].Response != null) otherwise "",
	"Q71_Indication_of_current_use_of_alcohol__c" : (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Responses[0].Response default "" )
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Question == "Indication of current use of alcohol:") otherwise "",
	"Question_71_Additional_Answers__c" : ( (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Responses[1].Response default "" ) ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Responses[2].Response default "" ) ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Responses[3].Response default "" ) ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Responses[4].Response default "" ) ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Responses[5].Response default "" ) ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Responses[6].Response default "" ) ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Responses[7].Response default "" ) ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Responses[8].Response default "" ))
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][1].Responses[1].Response != null) otherwise "",
	"Q72_History_of_youth_s_alcohol_use__c" : flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Question == "History of youth's alcohol use:") otherwise "",
	"Question_72_Additional_Answers__c" : ((flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses[1].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses[2].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses[3].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses[4].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses[5].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses[6].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses[7].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses[8].Response default ""))
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][2].Responses[1].Response != null) otherwise "",
	"Q73_Youth_s_drug_use__c" : flowVars.inputData.QuestionResponses["Substance Abuse"][3].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][3].Question == "Youth's drug use:") otherwise "",
	"Question_73_Additional_Answers__c" : ((flowVars.inputData.QuestionResponses["Substance Abuse"][3].Responses[1].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][3].Responses[2].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][3].Responses[3].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][3].Responses[4].Response default ""))
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][3].Responses[1].Response != null) otherwise "",
	"Q74_Indication_of_current_drug_use__c" : flowVars.inputData.QuestionResponses["Substance Abuse"][4].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][4].Question == "Indication of current drug use:") otherwise "",
	"Question_74_Additional_Answers__c" : ((flowVars.inputData.QuestionResponses["Substance Abuse"][4].Responses[1].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][4].Responses[2].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][4].Responses[3].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][4].Responses[4].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][4].Responses[5].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][4].Responses[6].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][4].Responses[7].Response default ""))
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][4].Responses[1].Response != null) otherwise "",
	"Q75_Types_of_drugs_currently_used__c" : (("No current use of drugs;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses map $.Response contains "No current use of drugs") otherwise "" ) ++ 
		("Currently using drugs;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses map $.Response contains "Currently using drugs") otherwise "" )  ),
	"Question_75_Additional_Answers__c" : (("Marijuana/Hashish;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses map $.Response contains "Marijuana/Hashish") otherwise "" ) ++
		("Inhalants (Glue/Gasoline);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses map $.Response contains "Inhalants (Glue/Gasoline)") otherwise "" ) ++ 
		("Cocaine (Crack/Rock);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses map $.Response contains "Cocaine (Crack/Rock)") otherwise "" ) ++  
		("Cocaine (Coke);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses map $.Response contains "Cocaine (Coke)") otherwise "" ) ++ 
		("Amphetamines (Meth/Uppers/Speed);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses map $.Response contains "Amphetamines (Meth/Uppers/Speed)") otherwise "" ) ++  
		("Ecstasy;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses map $.Response contains "Ecstasy") otherwise "" ) ++ 
		("Barbiturates (Tuinal/Seconal/Downers);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses map $.Response contains "Barbiturates (Tuinal/Seconal/Downers)") otherwise "" ) ++
		("Tranquilizers/Sedatives (Valium/Librium/Dalmane/Ketamine);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses map $.Response contains "Tranquilizers/Sedatives (Valium/Librium/Dalmane/Ketamine)") otherwise "" ) ++  
		("Hallucinogens (LSD/Acid/Mushrooms/GHB);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses map $.Response contains "Hallucinogens (LSD/Acid/Mushrooms/GHB)") otherwise "" ) ++  
		("Phencyclidine (PCP/Angel Dust);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses map $.Response contains "Phencyclidine (PCP/Angel Dust)") otherwise "" ) ++ 
		("Heroin;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses map $.Response contains "Heroin") otherwise "" ) ++ 
		("Other opiates (Dilaudid/Demerol/Percodan/Codeine/Oxycontin);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses map $.Response contains "Other opiates (Dilaudid/Demerol/Percodan/Codeine/Oxycontin)") otherwise "" ) ++ 
		("OTCs (Cough Syrup, Sudafed, etc);" when (flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses map $.Response contains "OTCs (Cough Syrup, Sudafed, etc)") otherwise "" ) ++ 
		("Prescription medication(s) not prescribed to youth;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses map $.Response contains "Prescription medication(s) not prescribed to youth") otherwise "" ) ++ 
		("Prescription medication(s) prescribed to youth;" when (flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses map $.Response contains "Prescription medication(s) prescribed to youth") otherwise "" ) ++ 
		("Other drugs" when (flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses map $.Response contains "Other drugs") otherwise "" )),
	//"Question_75_Describe__c" : ((flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses[-3].Response ++ ";" ++ flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses[-2].Response ++ ";" ++ flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses[-1].Response)  when (flowVars.inputData.QuestionResponses["Substance Abuse"][5].Responses map $.Response contains ["Prescription medication(s) not prescribed to youth","Prescription medication(s) prescribed to youth","Other drugs"]) otherwise "" ) ,
	"Question_75_Describe__c" : (payload."Question_75_Describe__c"[0] default "") ++ ";" ++ (payload."Question_75_Describe__c"[1] default "") ++ ";"  ++ (payload."Question_75_Describe__c"[3] default ""),
	"Q76_SA_treatment_program_participation__c" : flowVars.inputData.QuestionResponses["Substance Abuse"][6].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][6].Question == "Current drug/alcohol treatment program participation:") otherwise "",
	"Q77_History_of_youth_s_drug_use__c" : flowVars.inputData.QuestionResponses["Substance Abuse"][7].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][7].Question == "History of youth's drug use:") otherwise "",
	"Question_77_Additional_Answers__c" : ((flowVars.inputData.QuestionResponses["Substance Abuse"][7].Responses[1].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][7].Responses[2].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][7].Responses[3].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][7].Responses[4].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][7].Responses[5].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][7].Responses[6].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][7].Responses[7].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][7].Responses[8].Response default "") )
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][7].Responses[1].Response != null) otherwise "",
	"Q78_History_of_referrals_SA_assessment__c" : flowVars.inputData.QuestionResponses["Substance Abuse"][8].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][8].Question == "History of referrals for drug/alcohol assessment:") otherwise "",
	"Q79_History_attending_SA_education_class__c" : flowVars.inputData.QuestionResponses["Substance Abuse"][9].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][9].Question == "History of attending drug/alcohol education classes for a drug/alcohol problem:") otherwise "",
	"Q80_History_participating_in_SA_program__c" : flowVars.inputData.QuestionResponses["Substance Abuse"][10].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][10].Question == "History of participating in drug/alcohol treatment program:") otherwise "",
	"Q81_History_of_residential_SA_treatment__c" : ((flowVars.inputData.QuestionResponses["Substance Abuse"][11].Responses[0].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][11].Responses[1].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Substance Abuse"][11].Responses[2].Response default ""))
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][11].Question == "History of participation in residential drug/alcohol treatment:") otherwise "",
	"Q82_Status_of_SA_goals__c" : flowVars.inputData.QuestionResponses["Substance Abuse"][12].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Substance Abuse"][12].Question == "Status of substance abuse goals:") otherwise "",
	"Q83_Current_suicidal_ideation__c" : (("Does not currently have serious thoughts about suicide;" when (flowVars.inputData.QuestionResponses["Mental Health"][0].Responses map $.Response contains ("Does not currently have serious thoughts about suicide") ) otherwise "") ++ 
		 ("Feels life is not worth living - no hope for future;" when (flowVars.inputData.QuestionResponses["Mental Health"][0].Responses map $.Response contains ("Feels life is not worth living - no hope for future")) otherwise "") ++ 
		 ("Has serious thoughts about suicide;" when (flowVars.inputData.QuestionResponses["Mental Health"][0].Responses map $.Response contains ("Has serious thoughts about suicide")) otherwise "" ) ++ 
		 ("Knew someone well who has committed suicide;" when (flowVars.inputData.QuestionResponses["Mental Health"][0].Responses map $.Response contains ("Knew someone well who has committed suicide")) otherwise "") ++ 
		 ("Has made a plan to commit suicide;" when (flowVars.inputData.QuestionResponses["Mental Health"][0].Responses map $.Response contains ("Has made a plan to commit suicide")) otherwise "") ++ 
		 ("Engages in self-mutilating behavior;" when (flowVars.inputData.QuestionResponses["Mental Health"][0].Responses map $.Response contains ("Engages in self-mutilating behavior")) otherwise "") ++ 
		 ("Has recently attempted to commit suicide" when (flowVars.inputData.QuestionResponses["Mental Health"][0].Responses map $.Response contains ("Has recently attempted to commit suicide")) otherwise "" )) ,
	"Question_83_Additional_Answers__c" : (payload.Question_83_Additional_Answers__c[0] default "") ++ ";" ++ (payload.Question_83_Additional_Answers__c[1] default "") ++ ";" ++ (payload.Question_83_Additional_Answers__c[2] default "") ++ ";" ++ (payload.Question_83_Additional_Answers__c[3] default ""),
	"Question_83_Known_Person_Suicide_Date__c" : (((payload.Question_83_Known_Person_Suicide_Date__c[0] ++ |05:00|) as :date {format : "yyyy-MM-dd"} ) default ""),
	"Question_83_Attempted_Suicide_Date__c" : (((payload."Question_83_Attempted_Suicide_Date__c"[0] ++ |05:00|)  as :date {format : "yyyy-MM-dd"} ) default "" ),
	"Q84_History_of_suicidal_ideation__c" : (("Has never had serious thoughts about suicide;" when (flowVars.inputData.QuestionResponses["Mental Health"][1].Responses map $.Response contains ("Has never had serious thoughts about suicide") ) otherwise "") ++
		 ("Has felt life is not worth living - no hope for future;" when (flowVars.inputData.QuestionResponses["Mental Health"][1].Responses map $.Response contains ("Has felt life is not worth living - no hope for future")) otherwise "") ++
		 ("Has had serious thoughts about suicide;" when (flowVars.inputData.QuestionResponses["Mental Health"][1].Responses map $.Response contains ("Has had serious thoughts about suicide")) otherwise "" ) ++ 
		 ("Knew someone well who has committed suicide;" when (flowVars.inputData.QuestionResponses["Mental Health"][1].Responses map $.Response contains ("Knew someone well who has committed suicide")) otherwise "") ++ 
		 ("Has made a plan to commit suicide.;" when (flowVars.inputData.QuestionResponses["Mental Health"][1].Responses map $.Response contains ("Has made a plan to commit suicide.")) otherwise "") ++
		 ("Has engaged in self-mutilating behavior;" when (flowVars.inputData.QuestionResponses["Mental Health"][1].Responses map $.Response contains ("Has engaged in self-mutilating behavior")) otherwise "") ++ 
		 ("Has attempted to commit suicide." when (flowVars.inputData.QuestionResponses["Mental Health"][1].Responses map $.Response contains ("Has attempted to commit suicide.")) otherwise "" )),
	"Question_84_Additional_Answers__c" : (payload.Question_84_Additional_Answers__c[0] default "") ++ ";" ++ (payload.Question_84_Additional_Answers__c[1] default "") ++ ";" ++ (payload.Question_84_Additional_Answers__c[2] default "") ++ ";" ++ (payload.Question_84_Additional_Answers__c[3] default ""),
	"Question_84_Attempted_Suicide_Date__c" : ((payload.Question_84_Attempted_Suicide_Date__c[0] as :date {format : "yyyyMMdd"} as :string {format: "yyyy-MM-dd"} ) default ""), 
	"Q85_Currently_diagnosed_with_ADD_ADHD__c" : flowVars.inputData.QuestionResponses["Mental Health"][2].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Mental Health"][2].Question == "Currently diagnosed with ADD/ADHD:") otherwise "",
	"Question_85_Additional_Answers__c" : ((flowVars.inputData.QuestionResponses["Mental Health"][2].Responses[1].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][2].Responses[2].Response default ""))
	when (flowVars.inputData.QuestionResponses["Mental Health"][2].Responses[1].Response != null) otherwise "",
	"Q86_MH_counseling_currently_recommended__c" : flowVars.inputData.QuestionResponses["Mental Health"][3].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Mental Health"][3].Question == "Mental health counseling currently recommended (excluding ADD/ADHD treatment):") otherwise "",
	"Question_86_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Mental Health"][3].Responses[1].Response 
	when ( flowVars.inputData.QuestionResponses["Mental Health"][3].Responses[1].Response != null) otherwise "",
	"Q87_MH_medication_currently_prescribed__c" : flowVars.inputData.QuestionResponses["Mental Health"][4].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Mental Health"][4].Question == "Mental health medication currently prescribed (excluding ADD/ADHD medication):") otherwise "",
	"Q88_History_of_MH_concerns__c" : flowVars.inputData.QuestionResponses["Mental Health"][5].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Mental Health"][5].Question == "History of mental health concerns:") otherwise "",
	"Question_87_Additional_Answers__c" : (payload.Question_88_Additional_Answers__c[0] default ""),
	"Q89_History_of_placement_in_MH_RTC__c" : ((flowVars.inputData.QuestionResponses["Mental Health"][6].Responses[0].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][6].Responses[1].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][6].Responses[2].Response default ""))
	when (flowVars.inputData.QuestionResponses["Mental Health"][6].Question == "History of placement in a mental health residential treatment facility:") otherwise "",
	"Q90_Currently_have_health_insurance__c" : flowVars.inputData.QuestionResponses["Mental Health"][7].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Mental Health"][7].Question == "Does youth currently have health insurance?") otherwise "",
	"Question_90_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Mental Health"][7].Responses[1].Response 
	when ( flowVars.inputData.QuestionResponses["Mental Health"][7].Responses[1].Response != null) otherwise "",
	"Q91_History_of_violence_physical_abuse__c" : ((flowVars.inputData.QuestionResponses["Mental Health"][8].Responses[0].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][8].Responses[1].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][8].Responses[2].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][8].Responses[3].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][8].Responses[4].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][8].Responses[5].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][8].Responses[6].Response default "") ) 
	when (flowVars.inputData.QuestionResponses["Mental Health"][8].Question == "History of violence/physical abuse:") otherwise "",
	"Q92_History_of_witnessing_violence__c" : ((flowVars.inputData.QuestionResponses["Mental Health"][9].Responses[0].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][9].Responses[1].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][9].Responses[2].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][9].Responses[3].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][9].Responses[4].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][9].Responses[5].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][9].Responses[6].Response default "")) 
	when (flowVars.inputData.QuestionResponses["Mental Health"][9].Question == "History of witnessing violence:") otherwise "",
	"Q93_History_of_sexual_abuse_rape__c" : ((flowVars.inputData.QuestionResponses["Mental Health"][10].Responses[0].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][10].Responses[1].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][10].Responses[2].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][10].Responses[3].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][10].Responses[4].Response default "") ++ ";" ++ (flowVars.inputData.QuestionResponses["Mental Health"][10].Responses[5].Response default "")) 
	when (flowVars.inputData.QuestionResponses["Mental Health"][10].Question == "History of sexual abuse/rape:") otherwise "",
	"Q94_History_of_depression_anxiety__c" : flowVars.inputData.QuestionResponses["Mental Health"][11].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Mental Health"][11].Question == "History of depression/anxiety:") otherwise "",
	"Q95_History_of_thought_disturbance__c" : flowVars.inputData.QuestionResponses["Mental Health"][12].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Mental Health"][12].Question == "History of thought disturbance:") otherwise "",
	"Q96_History_of_traumatic_experience__c" : flowVars.inputData.QuestionResponses["Mental Health"][13].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Mental Health"][13].Question == "History of traumatic experience:") otherwise "",
	"Q97_Main_emotion_during_delinquent_acts__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][0].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][0].Question == "Primary emotion when committing acts of delinquency:") otherwise "",
	"Q98_Main_reason_for_delinquent_acts_6mos__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][1].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][1].Question == "Primary purpose for committing acts of delinquency within the last 6 months:") otherwise "",
	"Question_98_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][1].Responses[1].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][1].Responses[1].Response != null) otherwise "",
	"Q99_Level_of_optimism__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][2].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][2].Question == "Level of optimism:") otherwise "",
	"Q100_Level_of_impulsiveness__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][3].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][3].Question == "Level of impulsiveness:") otherwise "",
	"Q101_Control_of_anti_social_behavior__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][4].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][4].Question == "Belief in control over anti-social behavior:") otherwise "",
	"Q102_Empathy_for_victim_of_delinquency__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][5].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][5].Question == "Empathy, remorse, sympathy, or feelings for the victim(s) of delinquent behavior:") otherwise "",
	"Q103_Respect_for_property_of_others__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][6].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][6].Question == "Respect for property of others:") otherwise "",
	"Q104_Respect_for_authority_figures__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][7].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][7].Question == "Respect for authority figures:") otherwise "",
	"Q105_Youth_belief_laws_apply_to_them__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][8].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][8].Question == "Attitude toward responsible law abiding behavior:") otherwise "",
	"Q106_Tasks_assigned__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][9].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][9].Question == "Tasks assigned (include any supervised tasks or jobs):") otherwise "",
	"Q107_Behavior_related_to_assigned_tasks__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][10].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][10].Question == "Behavior related to assigned tasks:") otherwise "",
	"Q108_Anti_social_behavior_responsibility__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][11].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][11].Question == "Accepts responsibility for anti-social behavior:") otherwise "",	
	"Q109_Belief_in_meeting_conditions__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][12].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][12].Question == "Youth's belief in successfully meeting conditions of court supervision:") otherwise "",
	"Question_109_Additional_Answers__c" : flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][12].Responses[1].Response 
	when (flowVars.inputData.QuestionResponses["Attitudes and Behaviors"][12].Responses[1].Response != null) otherwise "",
	"Q110_Tolerance_for_frustration__c" : flowVars.inputData.QuestionResponses["Aggression"][0].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Aggression"][0].Question == "Tolerance for frustration:") otherwise "",
	"Q111_Perception_of_intentions_of_others__c" : flowVars.inputData.QuestionResponses["Aggression"][1].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Aggression"][1].Question == "Perception of intentions of others (in common, non-confrontational settings):") otherwise "",
	"Q112_Belief_in_verbal_aggression__c" : flowVars.inputData.QuestionResponses["Aggression"][2].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Aggression"][2].Question == "Belief in yelling and verbal aggression to resolve a disagreement or conflict:") otherwise "",
	"Q113_Belief_in_physical_aggression__c" : flowVars.inputData.QuestionResponses["Aggression"][3].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Aggression"][3].Question == "Belief in fighting and physical aggression to resolve a disagreement or conflict:") otherwise "",
	"Q114_Reports_of_violence__c" : (("No reports or evidence of violence;" when (flowVars.inputData.QuestionResponses["Aggression"][4].Responses map $.Response contains ("No reports or evidence of violence")) otherwise "") ++  
		("Violent outbursts, displays of temper, uncontrolled anger indicating potential for harm;" when (flowVars.inputData.QuestionResponses["Aggression"][4].Responses map $.Response contains ("Violent outbursts, displays of temper, uncontrolled anger indicating potential for harm")) otherwise "") ++ 
		("Deliberately inflicting physical pain;" when (flowVars.inputData.QuestionResponses["Aggression"][4].Responses map $.Response contains ("Deliberately inflicting physical pain")) otherwise "") ++ 
		("Using a weapon or threatening with a weapon;" when (flowVars.inputData.QuestionResponses["Aggression"][4].Responses map $.Response contains ("Using a weapon or threatening with a weapon")) otherwise "") ++ 
		("Fire starting;" when (flowVars.inputData.QuestionResponses["Aggression"][4].Responses map $.Response contains ("Fire starting")) otherwise "") ++ 
		("Violent destruction of property;" when (flowVars.inputData.QuestionResponses["Aggression"][4].Responses map $.Response contains ("Violent destruction of property")) otherwise "") ++ 
		("Animal cruelty;" when (flowVars.inputData.QuestionResponses["Aggression"][4].Responses map $.Response contains ("Animal cruelty")) otherwise "") ++ 
		("Verbal threats of violence or aggression" when (flowVars.inputData.QuestionResponses["Aggression"][4].Responses map $.Response contains ("Verbal threats of violence or aggression")) otherwise "") ),
	"Question_114_Additional__c" : (payload.Question_114_Additional_Answers__c[0] default "") , 
	"Q115_Displays_consequential_thinking__c" : flowVars.inputData.QuestionResponses["Skills"][0].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Skills"][0].Question == "Does the youth display consequential thinking?") otherwise "",
	"Q116_Practices_goal_setting__c" : flowVars.inputData.QuestionResponses["Skills"][1].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Skills"][1].Question == "Does the youth practice goal setting?") otherwise "",
	"Q117_Displays_problem_solving_behaviors__c" : flowVars.inputData.QuestionResponses["Skills"][2].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Skills"][2].Question == "Does the youth display problem-solving behaviors?") otherwise "",
	"Q118_Use_of_pro_social_skills__c" : flowVars.inputData.QuestionResponses["Skills"][3].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Skills"][3].Question == "Can the youth determine when to use pro-social skills and which skill to use?") otherwise "",
	"Q119_Social_skills_dealing_w_others__c" : flowVars.inputData.QuestionResponses["Skills"][4].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Skills"][4].Question == "How does the youth deal with others?") otherwise "",
	"Q120_Ability_to_deal_w_hard_situations__c" : flowVars.inputData.QuestionResponses["Skills"][5].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Skills"][5].Question == "How does the youth deal with difficult situations?") otherwise "",
	"Q121_Youth_monitors_internal_triggers__c" : flowVars.inputData.QuestionResponses["Skills"][6].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Skills"][6].Question == "Does the youth monitor/control internal triggers or (Thinking patterns), distorted thoughts (examples of distorted thoughts/feelings), that can lead to trouble?") otherwise "",
	"Q122_Youth_monitors_external_triggers__c" : flowVars.inputData.QuestionResponses["Skills"][7].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Skills"][7].Question == "Does the youth monitor/control external triggers, events or situations, that can lead to trouble") otherwise "",
	"Q123_Level_of_control_over_impulsiveness__c" : flowVars.inputData.QuestionResponses["Skills"][8].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Skills"][8].Question == "Level of control over impulsive behaviors that get the youth into trouble") otherwise "",
	"Q124_Alternatives_to_aggression_use__c" : flowVars.inputData.QuestionResponses["Skills"][9].Responses[0].Response 
	when (flowVars.inputData.QuestionResponses["Skills"][9].Question == "Does the youth use alternatives to aggression") otherwise ""
		
	
}