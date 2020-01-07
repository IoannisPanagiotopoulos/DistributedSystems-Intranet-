package DataBase;

public class Enums{
public enum  Dept{
Dietics,Economics,Informatics,Geography
}
public enum Activable{
 active,inactive, STRING
}
public enum empstatus{
 emp,unemp
}
public enum Role{
 Admin,Supervisor,Officer
}
public static  Activable StringtoEnumCoverterActivable(String stringtobeenum) {
	if(stringtobeenum.equals("active")){
		return Activable.active;
	}
	else {
		return Activable.inactive;
	}
}

public  static empstatus StringtoEnumConverterempstatus(String stringtobeenumed) {
	if(stringtobeenumed.equals("emp")){
		return  empstatus.emp;
	}
	else if(stringtobeenumed.equals("unemp")) {
	   return empstatus.unemp;
	}
	else {
		return null;
	}
}
public static  Role StringtoEnumConverterRole(String stringtobeenumed) {
	if(stringtobeenumed.equals("Admin")) {
		return Role.Admin;
	}
	else if(stringtobeenumed.equals("Supervisor")) {
		return Role.Supervisor;
	}
	else if(stringtobeenumed.equals("Officer")) {
		return Role.Officer;
	}
	else {
		return null;
	}
}
public  static Dept StringtobeEnumConverterDept(String stringtobeenumed){
	if(stringtobeenumed.equals("Informatics")) {
		return Dept.Informatics;
	}
	else if(stringtobeenumed.equals("Economics")) {
		return Dept.Economics;
	}
	else if(stringtobeenumed.equals("Geography")) {
		return Dept.Geography;
	}
	else if(stringtobeenumed.equals("Dietics")) {
		return Dept.Dietics;
	}
	else {
		return null;
	}
}



}
