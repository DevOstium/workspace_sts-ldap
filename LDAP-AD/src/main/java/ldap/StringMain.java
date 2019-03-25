package ldap;

import java.util.Arrays;
import java.util.List;

public class StringMain {

	private static final String[] PUBLIC_MATCHERS = { "/h2-console/**","/cliente/**" };
	
	public static void main(String[] args) {
		for (String s : PUBLIC_MATCHERS) {
			System.out.println(s);
		}
		System.out.println("**************************************");
		for (String str : getPublicMatches()  ) {
			System.out.println(str);
		}
		System.out.println("**************************************");
		for (String str : getHasPerfis() ) {
			System.out.println(str);
		}
	}

	private static String[] getPublicMatches() {
		List<String> list = Arrays. asList("/COMERCIAL/**", "/LABORATORIO/**");
		String[] array = list.toArray(new String[0]);
		return array;
	}
	
	private static String[] getHasPerfis() {
		List<String> list = Arrays. asList( "USER", "ADMIN", "DIRETOR", "GERENTE" );
		String[] array = list.toArray(new String[0]);
	return array;
}
}
