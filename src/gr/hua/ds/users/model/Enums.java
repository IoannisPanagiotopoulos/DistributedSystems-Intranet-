package gr.hua.ds.users.model;

public class Enums {
	
	public enum Dept {
		Dietics, Economics, Informatics, Geography, All
	}

	public enum Activable {
		active, inactive, STRING
	}

	public enum Empstatus {
		emp, unemp
	}

	public enum Role {
		ROLE_ADMIN, ROLE_SUPERVISOR, ROLE_OFFICER, ROLE_STUDENT
	}

	public static Activable StringtoEnumCoverterActivable(String stringtobeenum) {
		if (stringtobeenum.equals("active")) {
			return Activable.active;
		} else {
			return Activable.inactive;
		}
	}

	public static Empstatus StringtoEnumConverterempstatus(String stringtobeenumed) {
		if (stringtobeenumed.equals("emp")) {
			return Empstatus.emp;
		} else if (stringtobeenumed.equals("unemp")) {
			return Empstatus.unemp;
		} else {
			return null;
		}
	}

	public static Role StringtoEnumConverterRole(String stringtobeenumed) {
		if (stringtobeenumed.equals("Admin")) {
			return Role.ROLE_ADMIN;
		} else if (stringtobeenumed.equals("Supervisor")) {
			return Role.ROLE_SUPERVISOR;
		} else if (stringtobeenumed.equals("Officer")) {
			return Role.ROLE_OFFICER;
		} else if (stringtobeenumed.equals("Student")){
			return Role.ROLE_STUDENT;
		} else {
			return null;
		}
	}

	public static Dept StringtobeEnumConverterDept(String stringtobeenumed) {
		if (stringtobeenumed.equals("Informatics")) {
			return Dept.Informatics;
		} else if (stringtobeenumed.equals("Economics")) {
			return Dept.Economics;
		} else if (stringtobeenumed.equals("Geography")) {
			return Dept.Geography;
		} else if (stringtobeenumed.equals("Dietics")) {
			return Dept.Dietics;
		} else if (stringtobeenumed.equals("All")) {
			return null;
		} else {
			return null;
		}
	}

}
