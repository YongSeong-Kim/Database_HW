package db4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;



public class DBProject {

	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
	    
		while(true)
		{
			System.out.println("0. 프로그램 종료.");
			System.out.println("1. musicManager");
			System.out.println("2. streamUser");
			System.out.println("3. manage_user");
			System.out.println("4. chart");
			System.out.println("5. manage_chart");
			System.out.println("6. playlist");
			System.out.println("7. commentBox");
			System.out.println("8. manage_music");
			System.out.println("9. register");
			System.out.println("10. save_playlist");
			System.out.println("11. music");
			System.out.print("수행할 테이블 메뉴를 선택해주세요. : ");
			
			int cmd = scan.nextInt();
			
			if(cmd == 0)
			{
				System.exit(0);
			}
			else if(cmd == 1)
			{
				musicManager();
			}
			else if(cmd == 2)
			{
				streamUser();
			}
			else if(cmd == 3)
			{
				manage_user();
			}
			else if(cmd == 4)
			{
				chart();
			}
			else if(cmd == 5)
			{
				manage_chart();
			}
			else if(cmd == 6)
			{
				playlist();
			}
			else if(cmd == 7)
			{
				commentBox();
			}
			else if(cmd == 8)
			{
				manage_music();
			}
			else if(cmd == 9)
			{
				register();
			}
			else if(cmd == 10)
			{
				save_playlist();
			}
			else if(cmd == 11)
			{
				music();
			}		
					
		}
			
	}
	
	public static void musicManager()
	{
		while(true)
		{
			System.out.println("\n여기는 musicManager");
			tableCommand();
			Scanner s = new Scanner(System.in);
			int cmd = s.nextInt();
			System.out.println();
			if(cmd == 0)
			{
				break;
			}
			else if(cmd == 1)
			{
				System.out.println("추가할 column 순서대로 입력해주세요. 단, 작성시 ,를 통해 구분해주시기 바랍니다.");
				System.out.println("phoneNumber는 - 를 제외하고 입력해주세요.");
				System.out.println("IDNumber는 9자리 숫자로 입력해주세요.");
				System.out.println("ResidentNumber는 13자리 숫자로 입력해주세요.");
				System.out.println("Name phoneNumber Address IDNumber ResidentNumber");
				System.out.print("CMD > ");
				s.nextLine();
				String column = s.nextLine();
				String[] splitValues = column.split(",");
				insert(splitValues,"musicManager");
			}
			else if(cmd == 2)
			{
				System.out.println("musicManager의 column\n" + "Name phoneNumber Address IDNumber ResidentNumber");
				
				System.out.println("update할 정보를 입력해주세요. ','를 통해 구별하여 입력해주시기 바랍니다.");
				System.out.println("phoneNumber는 - 를 제외하고 입력해주세요.");
				System.out.println("IDNumber는 9자리 숫자로 입력해주세요.");
				System.out.println("ResidentNumber는 13자리 숫자로 입력해주세요.");
				System.out.println("예시 : 이름을 Kim으로 바꾸고 주소를 인천으로 바꾸기.");
				System.out.println("name = 'Kim', Address = 'Incheon' ");
				System.out.print("CMD > ");
				s.nextLine();
				String set = s.nextLine();
				System.out.println();
				System.out.println("update를 시킬 조건을 입력해주세요. and or 등호와 부등호를 이용해주시기 바랍니다.");
				System.out.println("예시 : 주소가 강릉이고 이름이 이씨인 사람의 조건.");
				System.out.println("address = 'Gangneung' and name = 'Lee' ");
				System.out.print("CMD > ");
				String condition = s.nextLine();
				update("musicManager",set,condition);
			}
			else if(cmd == 3)
			{
				System.out.println("musicManager의 column입니다.\n Name phoneNumber Address IDNumber ResidentNumber");
				System.out.println("삭제할 조건을 입력해주세요. 등호와 부등호, and or 연산을 사용하여 입력하세요. 조건이없다면 엔터를 치시기 바랍니다.");
				System.out.println("phoneNumber는 - 를 제외하고 입력해주세요.");
				System.out.println("IDNumber는 9자리 숫자로 입력해주세요.");
				System.out.println("ResidentNumber는 13자리 숫자로 입력해주세요.");
				System.out.println("예시 : 주소가 강릉이고 이름이 이씨인 사람의 조건.");
				System.out.println("address = 'Gangneung' and name = 'Lee' ");
				System.out.print("CMD > ");
				s.nextLine();
				String condition = s.nextLine();
				System.out.println();
				delete("musicManager", condition);
			}
			else if(cmd == 4)
			{
				System.out.println("보고싶은 정보를 차례대로 입력해주세요. ,를 통해 column을 구별해 입력하세요. (모든 정보를 원하면 *를 입력하세요.)  ");
				System.out.println("Name, phoneNumber, Address, IDNumber, ResidentNumber");
				System.out.println("phoneNumber는 - 를 제외하고 입력해주세요.");
				System.out.println("IDNumber는 9자리 숫자로 입력해주세요.");
				System.out.println("ResidentNumber는 13자리 숫자로 입력해주세요.");
				System.out.print("CMD > ");
				s.nextLine();
				String column = s.nextLine();
				System.out.println();
				String[] splitColumn = column.split(",");
				
				System.out.println("조건을 입력하세요. 등호와 부등호를 사용하여 입력하세요. 조건이없다면 엔터를 치시기 바랍니다. ");
				System.out.print("CMD > ");
				String condition = s.nextLine();
				select(splitColumn,"musicManager", condition);
			}
		
		}	
	}
	public static void streamUser()
	{
		while(true)
		{
			System.out.println("\n여기는 streamUser table 입니다.");
			tableCommand();
			Scanner s = new Scanner(System.in);
			int cmd = s.nextInt();
			System.out.println();
			if(cmd == 0)
			{
				break;
			}
			else if(cmd == 1)
			{
				System.out.println("추가할 column 순서대로 입력해주세요. 단, 작성시 ,를 통해 구분해주시기 바랍니다.");
				System.out.println("phoneNumber는 - 를 제외하고 입력해주세요.");
				System.out.println("ResidentNumber는 13자리 숫자로 입력해주세요.");
				System.out.println("Name PhoneNumber Address ID Password ResidentNumber currentMusicNo");
				System.out.print("CMD > ");
				s.nextLine();
				String column = s.nextLine();
				System.out.println();
				String[] splitValues = column.split(",");
				insert(splitValues,"streamUser");
			}
			else if(cmd == 2)
			{
				System.out.println("streamUser의 column\n" + "Name PhoneNumber Address ID Password ResidentNumber currentMusicNo");
				
				System.out.println("단 currentMusicNo를 입력할 때는 ''을 쓰지말고 입력해주시기 바랍니다. ");
				System.out.println("phoneNumber는 - 를 제외하고 입력해주세요.");
				System.out.println("ResidentNumber는 13자리 숫자로 입력해주세요.");
				System.out.println("update할 정보를 입력해주세요. ','를 통해 구별하여 입력해주시기 바랍니다.");
				System.out.println("예시 : 이름을 Kim으로 바꾸고 주소를 인천으로 바꾸기.");
				System.out.println("name = 'Kim', Address = 'Incheon' ");
				System.out.print("CMD > ");
				s.nextLine();
				String set = s.nextLine();
				System.out.println();
				System.out.println("update를 시킬 조건을 입력해주세요. and or 등호와 부등호를 이용해주시기 바랍니다.");
				System.out.println("예시 : 주소가 강릉이고 이름이 이씨인 사람의 조건.");
				System.out.println("address = 'Gangneung' and name = 'Lee' ");
				System.out.print("CMD > ");
				String condition = s.nextLine();
				update("streamUser",set,condition);
			}
			else if(cmd == 3)
			{
				System.out.println("streamUser의 column입니다.\n Name PhoneNumber Address ID Password ResidentNumber currentMusicNo");
				
				System.out.println("삭제할 조건을 입력해주세요. 등호와 부등호, and or 연산을 사용하여 입력하세요. 조건이없다면 엔터를 치시기 바랍니다.");
				System.out.println("phoneNumber는 - 를 제외하고 입력해주세요.");
				System.out.println("ResidentNumber는 13자리 숫자로 입력해주세요.");
				System.out.println("단 currentMusicNo 조건을 입력할 때는 ''을 쓰지말고 입력해주시기 바랍니다. ");
				
				System.out.println("예시 : 주소가 강릉이고 이름이 이씨인 사람의 조건.");
				
				System.out.println("address = 'Gangneung' and name = 'Lee' ");
				System.out.print("CMD > ");
				s.nextLine();
				String condition = s.nextLine();
				System.out.println();
				delete("streamUser", condition);
			}
			else if(cmd == 4)
			{
				System.out.println("보고싶은 정보를 차례대로 입력해주세요. ,를 통해 column을 구별해 입력하세요. (모든 정보를 원하면 *를 입력하세요.)  ");
				System.out.println("phoneNumber는 - 를 제외하고 입력해주세요.");
				System.out.println("ResidentNumber는 13자리 숫자로 입력해주세요.");
				System.out.println("Name, PhoneNumber, Address, ID, Password, ResidentNumber, currentMusicNo");
				System.out.print("CMD > ");
				s.nextLine();
				String column = s.nextLine();
				String[] splitColumn = column.split(",");
				
				System.out.println("조건을 입력하세요. 등호와 부등호를 사용하여 입력하세요. 조건이없다면 엔터를 치시기 바랍니다. ");
				System.out.print("CMD > ");
				String condition = s.nextLine();
				System.out.println();
				select(splitColumn,"streamUser", condition);
			}
		}	
	}
	
	
	public static void manage_user()
	{
		while(true)
		{
			System.out.println("\nMusicManager가 StreamUser를 관리하는 manage_user table 입니다.");
			System.out.println("streamUser의 ResidentNo, musicManager IDNo");
			
			tableCommand();
			Scanner s = new Scanner(System.in);
			int cmd = s.nextInt();
			System.out.println();
			if(cmd == 0)
			{
				break;
			}
			else if(cmd == 1)
			{
				System.out.println("추가할 column 순서대로 입력해주세요. 단, 작성시 ,를 통해 구분해주시기 바랍니다.");
				
				System.out.println("ResidentNo, IDNo");
				System.out.print("CMD > ");
				s.nextLine();
				String column = s.nextLine();
				String[] splitValues = column.split(",");
				insert(splitValues,"manage_user");
			}
			else if(cmd == 2)
			{
				System.out.println("manage_user의 column\n" + "ResidentNo IDNo");
				
				System.out.println("update할 정보를 입력해주세요. ','를 통해 구별하여 입력해주시기 바랍니다.");
				System.out.println("예시 : 주민번호를 2011111234567으로 바꾸고 직원번호를 123456789로 바꾸기.");
				System.out.println("residentno = '2011111234567',idno = '123456789' ");
				System.out.print("CMD > ");
				s.nextLine();
				String set = s.nextLine();
				System.out.println("update를 시킬 조건을 입력해주세요. and or 등호와 부등호를 이용해주시기 바랍니다.");
				System.out.println("예시 : 직원번호가 111111111인 조건.");
				System.out.println(" idno = '111111111' ");
				System.out.print("CMD > ");
				String condition = s.nextLine();
				System.out.println();
				update("manage_user",set,condition);
			}
			else if(cmd == 3)
			{
				System.out.println("manage_user의 column입니다.\n ResidentNo IDNo");
				
				System.out.println("삭제할 조건을 입력해주세요. 등호와 부등호, and or 연산을 사용하여 입력하세요. 조건이없다면 엔터를 치시기 바랍니다.");
				System.out.println("예시 : 직원번호가 111111111인 조건.");
				System.out.println(" idno = '111111111' ");
				System.out.print("CMD > ");
				s.nextLine();
				String condition = s.nextLine();
				System.out.println();
				delete("manage_user", condition);
			}
			else if(cmd == 4)
			{
				System.out.println("보고싶은 정보를 차례대로 입력해주세요. ,를 통해 column을 구별해 입력하세요. (모든 정보를 원하면 *를 입력하세요.)  ");
				System.out.println("ResidentNo, IDNo");
				System.out.print("CMD > ");
				s.nextLine();
				String column = s.nextLine();
				String[] splitColumn = column.split(",");
				
				System.out.println("조건을 입력하세요. 등호와 부등호를 사용하여 입력하세요. 조건이없다면 엔터를 치시기 바랍니다. ");
				System.out.println("예시 : 직원번호가 111111111인 조건.");
				System.out.println(" idno = '111111111' ");
				System.out.print("CMD > ");
				String condition = s.nextLine();
				System.out.println();
				select(splitColumn,"manage_user", condition);
			}
		}	
	}
	
	public static void chart()
	{
		while(true)
		{
			System.out.println("\n여기는 chart table 입니다.");
			tableCommand();
			Scanner s = new Scanner(System.in);
			int cmd = s.nextInt();
			System.out.println();
			if(cmd == 0)
			{
				break;
			}
			else if(cmd == 1)
			{
				System.out.println("추가할 column 순서대로 입력해주세요. 단, 작성시 ,를 통해 구분해주시기 바랍니다.");
				
				System.out.println("Date, Time , ChartNumber , Name, Genre");
				System.out.println("예시 : 1990-01-01, 03:00, 1, Pop Chart, Pop ");
				System.out.print("CMD > ");
				s.nextLine();
				String column = s.nextLine();
				System.out.println();
				String[] splitValues = column.split(",");
				insert(splitValues,"chart");
			}
			else if(cmd == 2)
			{
				System.out.println("Chart의 column\n" + "Date, Time , ChartNumber , Name, Genre");
				
				System.out.println("update할 정보를 입력해주세요. ','를 통해 구별하여 입력해주시기 바랍니다.");
				System.out.println("chartNumber를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 차트이름을 R&B차트로 바꾸고 장르를 R&B로 바꾸기.");
				System.out.println(" Name = 'R&B Chart',Genre = 'R&B' ");
				System.out.print("CMD > ");
				s.nextLine();
				String set = s.nextLine();
				System.out.println();
				System.out.println("update를 시킬 조건을 입력해주세요. and or 등호와 부등호를 이용해주시기 바랍니다.");
				System.out.println("예시 : 장르가 POP이고 차트이름이 POP Chart인 조건.");
				System.out.println("chartNumber를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println(" 'Genre = 'POP' and Name = 'POP Chart' ");
				System.out.print("CMD > ");
				String condition = s.nextLine();
				System.out.println();
				update("chart",set,condition);
			}
			else if(cmd == 3)
			{
				System.out.println("Chart의 column입니다.\n" + "Date, Time , ChartNumber , Name, Genre");
				System.out.println("삭제할 조건을 입력해주세요. 등호와 부등호, and or 연산을 사용하여 입력하세요. 조건이 없다면 엔터를 치시기 바랍니다.");
				System.out.println("chartNumber를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 날짜가 1919년 01월 01일 차트인 조건.");
				System.out.println("Date = '1919-01-01' ");
				System.out.print("CMD > ");
				s.nextLine();
				String condition = s.nextLine();
				System.out.println();
				delete("chart", condition);
			}
			else if(cmd == 4)
			{
				System.out.println("보고싶은 정보를 차례대로 입력해주세요. ,를 통해 column을 구별해 입력하세요. (모든 정보를 원하면 *를 입력하세요.)  ");
				System.out.println("Date, Time , ChartNumber , Name, Genre");
				s.nextLine();
				String column = s.nextLine();
				System.out.println();
				String[] splitColumn = column.split(",");
				
				System.out.println("조건을 입력하세요. 등호와 부등호를 사용하여 입력하세요. 조건이없다면 엔터를 치시기 바랍니다. ");
				System.out.println("예시 : 날짜가 1919년 01월 01일 차트인 조건.");
				System.out.println("Date = '1919-01-01' ");
				System.out.print("CMD > ");
				String condition = s.nextLine();
				System.out.println();
				select(splitColumn,"chart", condition);
			}
		}	
	}
	
	public static void manage_chart()
	{
		while(true)
		{
			System.out.println("\nMusicManager가 Chart를 관리하는 manage_chart table 입니다.");
			
			tableCommand();
			Scanner s = new Scanner(System.in);
			int cmd = s.nextInt();
			System.out.println();
			if(cmd == 0)
			{
				break;
			}
			else if(cmd == 1)
			{
				System.out.println("추가할 column 순서대로 입력해주세요. 단, 작성시 ,를 통해 구분해주시기 바랍니다.");
				
				System.out.println("ChartNo, IDNo");
				System.out.println("예시 : 5, 123456789");
				System.out.print("CMD > ");
				s.nextLine();
				String column = s.nextLine();
				System.out.println();
				String[] splitValues = column.split(",");
				insert(splitValues,"manage_chart");
			}
			else if(cmd == 2)
			{
				System.out.println("manage_chart의 column\n" + "ChartNo, IDNo");
				
				System.out.println("update할 정보를 입력해주세요. ','를 통해 구별하여 입력해주시기 바랍니다.");
				System.out.println("chartNo를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				
				System.out.println("예시 : 차트번호를 5로 바꾸고 IDNo도 111111111로 바꾸기.");
				
				System.out.println(" ChartNo = 5, IDNo = '111111111' ");
				System.out.print("CMD > ");
				s.nextLine();
				String set = s.nextLine();
				System.out.println();
				System.out.println("update를 시킬 조건을 입력해주세요. and or 등호와 부등호를 이용해주시기 바랍니다.");
				System.out.println("chartNo를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 차트번호가 3이고 IDNo도 111111112인 조건");
				System.out.println(" ChartNo = 3 and IDNo = '111111112' ");
				System.out.print("CMD > ");
				String condition = s.nextLine();
				System.out.println();
				update("manage_chart",set,condition);
			}
			else if(cmd == 3)
			{
				System.out.println("manage_chart의 column입니다.\n" + "ChartNo, IDNo");
				System.out.println("삭제할 조건을 입력해주세요. 등호와 부등호, and or 연산을 사용하여 입력하세요. 조건이 없다면 엔터를 치시기 바랍니다.");
				System.out.println("chartNumber를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 차트번호가 3이고 IDNo도 111111112인 조건");
				System.out.println(" ChartNo = 3 and IDNo = '111111112' ");
				System.out.print("CMD > ");
				
				s.nextLine();
				String condition = s.nextLine();
				System.out.println();
				delete("manage_chart", condition);
			}
			else if(cmd == 4)
			{
				System.out.println("보고싶은 정보를 차례대로 입력해주세요. ,를 통해 column을 구별해 입력하세요. (모든 정보를 원하면 *를 입력하세요.)  ");
				System.out.println("ChartNo, IDNo");
				System.out.print("CMD > ");
				s.nextLine();
				String column = s.nextLine();
				System.out.println();
				String[] splitColumn = column.split(",");
				
				System.out.println("조건을 입력하세요. 등호와 부등호를 사용하여 입력하세요. 조건이없다면 엔터를 치시기 바랍니다. ");
				System.out.println("chartNumber를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 차트번호가 3이고 IDNo도 111111112인 조건");
				System.out.println(" ChartNo = 3 and IDNo = '111111112' ");
				System.out.print("CMD > ");
				String condition = s.nextLine();
				System.out.println();
				select(splitColumn,"manage_chart", condition);
			}
		}
	}
	
	public static void playlist()
	{
		while(true)
		{
			System.out.println("\n streamUser가 사용하는 playlist table 입니다.");
			
			tableCommand();
			Scanner s = new Scanner(System.in);
			int cmd = s.nextInt();
			System.out.println();
			if(cmd == 0)
			{
				break;
			}
			else if(cmd == 1)
			{
				System.out.println("추가할 column 순서대로 입력해주세요. 단, 작성시 ,를 통해 구분해주시기 바랍니다.");
				
				System.out.println("Name, PlaylistNumber, ResidentNo");
				System.out.println("예시 : 크리스마스 캐롤, 1,9911111234567");
				System.out.print("CMD > ");
				s.nextLine();
				String column = s.nextLine();
				System.out.println();
				String[] splitValues = column.split(",");
				insert(splitValues,"playlist");
			}
			else if(cmd == 2)
			{
				System.out.println("playlist의 column\n" + "Name, PlaylistNumber, ResidentNo");
				
				System.out.println("update할 정보를 입력해주세요. ','를 통해 구별하여 입력해주시기 바랍니다.");
				System.out.println("PlaylistNumber를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				
				System.out.println("예시 : 이름을 크리스마스 캐롤로 바꾸기.");
				
				System.out.println(" Name = '크리스마스 캐롤' ");
				System.out.print("CMD > ");
				s.nextLine();
				String set = s.nextLine();
				System.out.println();
				System.out.println("update를 시킬 조건을 입력해주세요. and or 등호와 부등호를 이용해주시기 바랍니다.");
				System.out.println("PlaylistNumber를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 이름이 힙합인 조건");
				System.out.println(" Name = '힙합' ");
				System.out.print("CMD > ");
				String condition = s.nextLine();
				System.out.println();
				update("playlist",set,condition);
			}
			else if(cmd == 3)
			{
				System.out.println("Playlist의 column입니다.\n" + "Name, PlaylistNumber, ResidentNo");
				System.out.println("삭제할 조건을 입력해주세요. 등호와 부등호, and or 연산을 사용하여 입력하세요. 조건이 없다면 엔터를 치시기 바랍니다.");
				System.out.println("PlaylistNumber를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 플레이리스트 번호가 3이고 주민번호가 9911111234567인 조건");
				System.out.println(" PlaylistNo = 3 and ResidentNo = '9911111234567' ");
				System.out.print("CMD > ");
				
				s.nextLine();
				String condition = s.nextLine();
				System.out.println();
				delete("playlist", condition);
			}
			else if(cmd == 4)
			{
				System.out.println("보고싶은 정보를 차례대로 입력해주세요. ,를 통해 column을 구별해 입력하세요. (모든 정보를 원하면 *를 입력하세요.)  ");
				System.out.println("Name, PlaylistNumber, ResidentNo");
				System.out.print("CMD > ");
				s.nextLine();
				String column = s.nextLine();
				System.out.println();
				String[] splitColumn = column.split(",");
				
				System.out.println("조건을 입력하세요. 등호와 부등호를 사용하여 입력하세요. 조건이없다면 엔터를 치시기 바랍니다. ");
				System.out.println("PlaylistNumber를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 플레이리스트 번호가 3이고 주민번호가 9911111234567인 조건");
				System.out.println(" PlaylistNo = 3 and ResidentNo = '9911111234567' ");
				System.out.print("CMD > ");
				String condition = s.nextLine();
				System.out.println();
				select(splitColumn,"playlist", condition);
			}
		}
	}
	
	public static void commentBox()
	{
		while(true)
		{
			System.out.println("여기는 commentBox table 입니다.");
			tableCommand();
			Scanner s = new Scanner(System.in);
			int cmd = s.nextInt();
			System.out.println();
			if(cmd == 0)
			{
				break;
			}
			else if(cmd == 1)
			{
				System.out.println("추가할 column 순서대로 입력해주세요. 단, 작성시 ,를 통해 구분해주시기 바랍니다.");
				
				System.out.println("Comment, MusciNo");
				System.out.println("예시 : 잘 들었습니다., 13");
				System.out.print("CMD > ");
				s.nextLine();
				String column = s.nextLine();
				System.out.println();
				String[] splitValues = column.split(",");
				insert(splitValues,"commentBox");
			}
			else if(cmd == 2)
			{
				System.out.println("commentBox의 column\n" + "Comment, MusciNo");
				
				System.out.println("update할 정보를 입력해주세요. ','를 통해 구별하여 입력해주시기 바랍니다.");
				System.out.println("MusciNo를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				
				System.out.println("예시 : 음악번호를 5로 바꾸기.");
				
				System.out.println(" MusicNo = 5 ");
				System.out.print("CMD > ");
				s.nextLine();
				String set = s.nextLine();
				System.out.println();
				System.out.println("update를 시킬 조건을 입력해주세요. and or 등호와 부등호를 이용해주시기 바랍니다.");
				System.out.println("MusciNo를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 음악번호가 3인 조건.");
				
				System.out.println(" MusicNo = 3 ");
				System.out.print("CMD > ");
				
				String condition = s.nextLine();
				System.out.println();
				update("commentBox",set,condition);
			}
			else if(cmd == 3)
			{
				System.out.println("commentBox의 column입니다.\n" + "Comment, MusciNo");
				System.out.println("삭제할 조건을 입력해주세요. 등호와 부등호, and or 연산을 사용하여 입력하세요. 조건이 없다면 엔터를 치시기 바랍니다.");
				System.out.println("MusicNo를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				
				System.out.println("예시 : 음악번호가 3인 조건.");
				
				System.out.println(" MusicNo = 3 ");
				System.out.print("CMD > ");
				
				
				s.nextLine();
				String condition = s.nextLine();
				System.out.println();
				delete("commentBox", condition);
			}
			else if(cmd == 4)
			{
				System.out.println("보고싶은  Column을 차례대로 입력해주세요. ,를 통해 column을 구별해 입력하세요. (모든 정보를 원하면 *를 입력하세요.)  ");
				System.out.println("Comment, MusciNo");
				s.nextLine();
				String column = s.nextLine();
				System.out.println();
				String[] splitColumn = column.split(",");
				
				System.out.println("조건을 입력하세요. 등호와 부등호를 사용하여 입력하세요. 조건이없다면 엔터를 치시기 바랍니다. ");
				System.out.println("MusciNo를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 음악번호가 3인 조건.");
				
				System.out.println(" MusicNo = 3 ");
				System.out.print("CMD > ");
				String condition = s.nextLine();
				System.out.println();
				select(splitColumn,"commentBox", condition);
			}
		}
	}
	
	public static void manage_music()
	{
		while(true)
		{
			System.out.println("\nMusicManager가 Music을 관리하는 manage_music table 입니다.");
			tableCommand();
			Scanner s = new Scanner(System.in);
			int cmd = s.nextInt();
			System.out.println();
			if(cmd == 0)
			{
				break;
			}
			else if(cmd == 1)
			{
				System.out.println("추가할 column 순서대로 입력해주세요. 단, 작성시 ,를 통해 구분해주시기 바랍니다.");
				
				System.out.println("IDNo, MusicNo");
				System.out.println("예시 : 123456789, 13");
				System.out.print("CMD > ");
				s.nextLine();
				String column = s.nextLine();
				System.out.println();
				String[] splitValues = column.split(",");
				insert(splitValues,"manage_music");
			}
			else if(cmd == 2)
			{
				System.out.println("manage_music의 column\n" + "IDNo, MusciNo");
				
				System.out.println("update할 정보를 입력해주세요. ','를 통해 구별하여 입력해주시기 바랍니다.");
				System.out.println("MusciNo를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				
				System.out.println("예시 : 음악번호를 5로 바꾸기.");
				
				System.out.println(" MusicNo = 5 ");
				System.out.print("CMD > ");
				s.nextLine();
				String set = s.nextLine();
				System.out.println();
				System.out.println("update를 시킬 조건을 입력해주세요. and or 등호와 부등호를 이용해주시기 바랍니다.");
				System.out.println("MusciNo를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 음악번호가 3인 조건.");
				
				System.out.println(" MusicNo = 3 ");
				System.out.print("CMD > ");
				
				String condition = s.nextLine();
				System.out.println();
				update("manage_music",set,condition);
			}
			else if(cmd == 3)
			{
				System.out.println("manage_music의 column입니다.\n" + "IDNo, MusciNo");
				System.out.println("삭제할 조건을 입력해주세요. 등호와 부등호, and or 연산을 사용하여 입력하세요. 조건이 없다면 엔터를 치시기 바랍니다.");
				System.out.println("MusicNo를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				
				System.out.println("예시 : 음악번호가 3인 조건.");
				
				System.out.println(" MusicNo = 3 ");
				System.out.print("CMD > ");
				
				s.nextLine();
				String condition = s.nextLine();
				System.out.println();
				delete("manage_music", condition);
			}
			else if(cmd == 4)
			{
				System.out.println("보고싶은  Column을 차례대로 입력해주세요. ,를 통해 column을 구별해 입력하세요. (모든 정보를 원하면 *를 입력하세요.)  ");
				System.out.println("IDNo, MusciNo");
				s.nextLine();
				String column = s.nextLine();
				System.out.println();
				String[] splitColumn = column.split(",");
				
				System.out.println("조건을 입력하세요. 등호와 부등호를 사용하여 입력하세요. 조건이없다면 엔터를 치시기 바랍니다. ");
				System.out.println("MusciNo를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 음악번호가 3인 조건.");
				
				System.out.println(" MusicNo = 3 ");
				System.out.print("CMD > ");
				String condition = s.nextLine();
				System.out.println();
				select(splitColumn,"manage_music", condition);
			}
		}
	}
	
	public static void register()
	{
		while(true)
		{
			System.out.println("\nChart에 등록하는 music을 관리하는 Register table 입니다.");
			tableCommand();
			Scanner s = new Scanner(System.in);
			int cmd = s.nextInt();
			System.out.println();
			if(cmd == 0)
			{
				break;
			}
			else if(cmd == 1)
			{
				System.out.println("추가할 column 순서대로 입력해주세요. 단, 작성시 ,를 통해 구분해주시기 바랍니다.");
				
				System.out.println("ChartNo, MusciNo");
				System.out.println("예시 : 1, 13");
				System.out.print("CMD > ");
				s.nextLine();
				String column = s.nextLine();
				System.out.println();
				String[] splitValues = column.split(",");
				insert(splitValues,"register");
			}
			else if(cmd == 2)
			{
				System.out.println("Register의 column\n" + "ChartNo, MusciNo");
				
				System.out.println("update할 정보를 입력해주세요. ','를 통해 구별하여 입력해주시기 바랍니다.");
				System.out.println("MusciNo와 ChartNo를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				
				System.out.println("예시 : 음악번호를 5로 바꾸기.");
				
				System.out.println(" MusicNo = 5 ");
				System.out.print("CMD > ");
				s.nextLine();
				String set = s.nextLine();
				System.out.println();
				System.out.println("update를 시킬 조건을 입력해주세요. and or 등호와 부등호를 이용해주시기 바랍니다.");
				System.out.println("MusciNo와 ChartNo를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 음악번호가 3인 조건.");
				
				System.out.println(" MusicNo = 3 ");
				System.out.print("CMD > ");
				
				String condition = s.nextLine();
				System.out.println();
				update("register",set,condition);
			}
			else if(cmd == 3)
			{
				System.out.println("Register의 column입니다.\n" + "ChartNo, MusciNo");
				System.out.println("삭제할 조건을 입력해주세요. 등호와 부등호, and or 연산을 사용하여 입력하세요. 조건이 없다면 엔터를 치시기 바랍니다.");
				System.out.println("ChartNo와 MusicNo를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				
				System.out.println("예시 : 음악번호가 3인 조건.");
				
				System.out.println(" MusicNo = 3 ");
				
				System.out.print("CMD > ");
				s.nextLine();
				String condition = s.nextLine();
				System.out.println();
				delete("register", condition);
			}
			else if(cmd == 4)
			{
				System.out.println("보고싶은  Column을 차례대로 입력해주세요. ,를 통해 column을 구별해 입력하세요. (모든 정보를 원하면 *를 입력하세요.)  ");
				System.out.println("ChartNo, MusciNo");
				System.out.print("CMD > ");
				s.nextLine();
				String column = s.nextLine();
				System.out.println();
				String[] splitColumn = column.split(",");
				
				System.out.println("조건을 입력하세요. 등호와 부등호를 사용하여 입력하세요. 조건이없다면 엔터를 치시기 바랍니다. ");
				System.out.println("ChartNo와 MusciNo를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 음악번호가 3인 조건.");
				
				System.out.println(" MusicNo = 3 ");
				System.out.print("CMD > ");
				String condition = s.nextLine();
				System.out.println();
				select(splitColumn,"register", condition);
			}
		}
	}
	
	public static void save_playlist()
	{
		while(true)
		{
			System.out.println("\n music을 playlist에 저장하는 save_playlist table 입니다.");
			tableCommand();
			Scanner s = new Scanner(System.in);
			int cmd = s.nextInt();
			System.out.println();
			if(cmd == 0)
			{
				break;
			}
			else if(cmd == 1)
			{
				System.out.println("추가할 column 순서대로 입력해주세요. 단, 작성시 ,를 통해 구분해주시기 바랍니다.");
				
				System.out.println("PlaylistNo, MusciNo");
				System.out.println("예시 : 3, 13");
				System.out.print("CMD > ");
				s.nextLine();
				String column = s.nextLine();
				System.out.println();
				String[] splitValues = column.split(",");
				insert(splitValues,"save_playlist");
			}
			else if(cmd == 2)
			{
				System.out.println("save_playlist의 column\n" + "PlaylistNo, MusciNo");
				
				System.out.println("update할 정보를 입력해주세요. ','를 통해 구별하여 입력해주시기 바랍니다.");
				System.out.println("PlaylistNo와 MusciNo를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				
				System.out.println("예시 : 음악번호를 5로 바꾸기.");
				
				System.out.println(" MusicNo = 5 ");
				System.out.print("CMD > ");
				s.nextLine();
				String set = s.nextLine();
				System.out.println();
				System.out.println("update를 시킬 조건을 입력해주세요. and or 등호와 부등호를 이용해주시기 바랍니다.");
				System.out.println("PlaylistNo와 MusciNo를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 음악번호가 3인 조건.");
				
				System.out.println(" MusicNo = 3 ");
				System.out.print("CMD > ");
				
				String condition = s.nextLine();
				System.out.println();
				update("save_playlist",set,condition);
			}
			else if(cmd == 3)
			{
				System.out.println("save_playlist의 column입니다.\n" + "PlaylistNo, MusciNo");
				System.out.println("삭제할 조건을 입력해주세요. 등호와 부등호, and or 연산을 사용하여 입력하세요. 조건이 없다면 엔터를 치시기 바랍니다.");
				System.out.println("PlaylistNo와 MusicNo를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				
				System.out.println("예시 : 음악번호가 3인 조건.");
				
				System.out.println(" MusicNo = 3 ");
				System.out.print("CMD > ");
				
				
				s.nextLine();
				String condition = s.nextLine();
				System.out.println();
				delete("save_playlist", condition);
			}
			else if(cmd == 4)
			{
				System.out.println("보고싶은  Column을 차례대로 입력해주세요. ,를 통해 column을 구별해 입력하세요. (모든 정보를 원하면 *를 입력하세요.)  ");
				System.out.println("PlaylistNo, MusciNo");
				System.out.print("CMD > ");
				s.nextLine();
				String column = s.nextLine();
				System.out.println();
				String[] splitColumn = column.split(",");
				
				System.out.println("조건을 입력하세요. 등호와 부등호를 사용하여 입력하세요. 조건이없다면 엔터를 치시기 바랍니다. ");
				System.out.println("PlaylistNo와 MusciNo를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 음악번호가 3인 조건.");
				
				System.out.println(" MusicNo = 3 ");
				System.out.print("CMD > ");
				String condition = s.nextLine();
				System.out.println();
				select(splitColumn,"save_playlist", condition);
			}
		}
	}
	
	public static void music()
	{
		while(true)
		{
			System.out.println("\n여기는 Music table 입니다.");
			tableCommand();
			Scanner s = new Scanner(System.in);
			int cmd = s.nextInt();
			System.out.println();
			if(cmd == 0)
			{
				break;
			}
			else if(cmd == 1)
			{
				System.out.println("추가할 column 순서대로 입력해주세요. 단, 작성시 ,를 통해 구분해주시기 바랍니다.");
				
				System.out.println("albumname , musicname, lyricwriter, composer, lyrics, videoTime, genre, musicnumber, playcount, releasedate");
				System.out.println("예시 : Love YourSelf, DNA, Pdogg, Hitman, 첫눈에 널 알아보게 됐어 서롤 불러왔던 것처럼...,3:40, Dance, 13, 130,2018-08-24");
				System.out.print("CMD > ");
				s.nextLine();
				String column = s.nextLine();
				System.out.println();
				String[] splitValues = column.split(",");
				insert(splitValues,"music");
			}
			else if(cmd == 2)
			{
				System.out.println("music의 column\n" + "albumname , musicname, lyricwriter, composer, lyrics, videoTime, genre, musicnumber, playcount, releasedate");
				
				System.out.println("update할 정보를 입력해주세요. ','를 통해 구별하여 입력해주시기 바랍니다.");
				System.out.println("MusciNumber와 PlayCount를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 앨범이름을 Answer로 바꾸고 작곡가를 KIM으로 바꾸기.");
				System.out.println(" AlbumName = 'Answer', Composer = 'KIM' ");
				System.out.print("CMD > ");
				s.nextLine();
				String set = s.nextLine();
				System.out.println();
				System.out.println("update를 시킬 조건을 입력해주세요. and or 등호와 부등호를 이용해주시기 바랍니다.");
				System.out.println("MusciNumber와 PlayCount를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 앨범이름이 LOVE YOURSELF인 조건.");
				System.out.println(" AlbumName = 'LOVE YOURSELF'");
				System.out.print("CMD > ");
				
				String condition = s.nextLine();
				System.out.println();
				update("music",set,condition);
			}
			else if(cmd == 3)
			{
				System.out.println("music의 column입니다.\n" + "albumname , musicname, lyricwriter, composer, lyrics, videoTime, genre, musicnumber, playcount, releasedate");
				System.out.println("삭제할 조건을 입력해주세요. 등호와 부등호, and or 연산을 사용하여 입력하세요. 조건이 없다면 엔터를 치시기 바랍니다.");
				System.out.println("MusciNumber와 PlayCount를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 앨범이름이 LOVE YOURSELF인 조건.");
				
				
				System.out.println(" AlbumName = 'LOVE YOURSELF'");
				System.out.print("CMD > ");
				
				s.nextLine();
				String condition = s.nextLine();
				System.out.println();
				delete("music", condition);
			}
			else if(cmd == 4)
			{
				System.out.println("보고싶은  Column을 차례대로 입력해주세요. ,를 통해 column을 구별해 입력하세요. (모든 정보를 원하면 *를 입력하세요.)  ");
				System.out.println("albumname , musicname, lyricwriter, composer, lyrics, videoTime, genre, musicnumber, playcount, releasedate");
				System.out.print("CMD > ");
				s.nextLine();
				String column = s.nextLine();
				System.out.println();
				String[] splitColumn = column.split(",");
				
				System.out.println("조건을 입력하세요. 등호와 부등호를 사용하여 입력하세요. 조건이없다면 엔터를 치시기 바랍니다. ");
				System.out.println("MusciNumber와 PlayCount를 조건으로 쓸때에는 ''을 생략하고 입력해주시기바랍니다.");
				System.out.println("예시 : 앨범이름이 LOVE YOURSELF인 조건.");
				
				
				System.out.println(" AlbumName = 'LOVE YOURSELF'");
				System.out.print("CMD > ");
				String condition = s.nextLine();
				System.out.println();
				select(splitColumn,"music", condition);
			}
		}
	}
	
	
	public static void insert(String[] splitValues, String table)
	{
		String driver = "org.mariadb.jdbc.Driver";
	    Connection con = null;
	    PreparedStatement pstmt;
	    ResultSet rs;
	    try {
	    	
            Class.forName(driver);
            
            con = DriverManager.getConnection(
                    "jdbc:mariadb://127.0.0.1:3306/musiccompany","a","rladydtjd2445");
            
            if( con != null ) {
                System.out.println("DB 접속 성공");
            }
            
            Statement stmt = con.createStatement();
    	    rs = stmt.executeQuery( "SELECT * FROM "+ table );
    	    
    	    ResultSetMetaData rsmd = rs.getMetaData();
            
    	    for(int i = 0; i < splitValues.length; i++)
            {
    	    	splitValues[i] = splitValues[i].trim();
            }
    	    String[] temp = null;
    	    
	    	temp = new String[rsmd.getColumnCount()];
	    	for(int i = 0; i < splitValues.length; i++)
            {
	    		if(splitValues[i].equals("")) splitValues[i] = null;
    	    	temp[i] = splitValues[i];
            }

            String sql = "INSERT into " + table + " values" + "( ";
            
            for(int i = 0; i < temp.length; i++)
            {
            	sql = sql + "?";
            	if(i + 1 != temp.length) sql = sql +",";
            }
            sql = sql + ")";
            
            pstmt = con.prepareStatement(sql);
            
            for(int i = 0; i < temp.length; i++)
            {
            	execute(rsmd.getColumnType(i + 1), i+1, temp[i], pstmt);
            }
            
            pstmt.executeUpdate();
            
        } catch (ClassNotFoundException e) { 
            System.out.println("드라이버 로드 실패");
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }
	}
	
	public static void update(String table, String set, String condition)
	{
		String driver = "org.mariadb.jdbc.Driver";
	    Connection con = null;
	    PreparedStatement pstmt;
	    ResultSet rs;
	    try {
            Class.forName(driver);
            con = DriverManager.getConnection(
                    "jdbc:mariadb://127.0.0.1:3306/musicCompany","a","rladydtjd2445");
            
            String sql;
            
            if(condition.equals("")) sql = "UPDATE " + table + " SET " + set;
            
            else sql = "UPDATE " + table + " SET " + set + " WHERE " + condition;
            
            
            if( con != null ) {
                System.out.println("DB 접속 성공");
            }
            
            pstmt = con.prepareStatement(sql);
            
            Statement stmt = con.createStatement();
    	    rs = stmt.executeQuery( sql );
    	    
        } catch (ClassNotFoundException e) { 
            System.out.println("드라이버 로드 실패");
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }
	}
	
	public static void delete(String table, String condition)
	{
		String driver = "org.mariadb.jdbc.Driver";
	    Connection con = null;
	    PreparedStatement pstmt;
	    ResultSet rs;
	    try {
            Class.forName(driver);
            con = DriverManager.getConnection(
                    "jdbc:mariadb://127.0.0.1:3306/musicCompany","a","rladydtjd2445");
            
            String sql = "DELETE FROM ";
            		
            if(condition.equals("")) sql = sql + table ;
            
            else sql = sql + table + " WHERE " + condition;

    	    Statement stmt = con.createStatement();
    	    stmt.execute( sql );

        } catch (ClassNotFoundException e) { 
            System.out.println("드라이버 로드 실패");
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }
		
		
        		
	}
	
	public static void select(String[] splitColumn, String table, String condition)
	{
		String driver = "org.mariadb.jdbc.Driver";
	    Connection con = null;
	    PreparedStatement pstmt;
	    ResultSet rs;
	    try {
            Class.forName(driver);
            con = DriverManager.getConnection(
                    "jdbc:mariadb://127.0.0.1:3306/musicCompany","a","rladydtjd2445");
            
            String sql = "SELECT ";
            
            for(int i = 0; i < splitColumn.length; i++)
            {
            	if(i != 0 ) sql = sql + ",";
            	sql = sql + splitColumn[i];
            }
            System.out.println();
            if(condition.equals(""))
            {
            	sql = sql + " FROM " + table ;
            }
            else
            {
            	sql = sql + " FROM " + table + " WHERE " + condition;
            }
            
            if( con != null ) {
                System.out.println("DB 접속 성공");
            }
            
    	    Statement stmt = con.createStatement();
    	    rs = stmt.executeQuery( sql );
    	    
    	    ResultSetMetaData rsmd = rs.getMetaData();
    	    
    	    if(splitColumn[0].equals("*"))
    	    {
    	    	splitColumn = new String[rsmd.getColumnCount()];
    	    	for(int i = 0; i < rsmd.getColumnCount(); i++)
    	    	{
    	    		splitColumn[i] = rsmd.getColumnName(i + 1);
    	    	}
    	    }
    	    
    	    while ( rs.next() ) {
    	    	
    	    	for(int i = 1; i <= splitColumn.length; i++)
    	    	{
    	    		printSelectValue(rsmd.getColumnType(i), splitColumn[i - 1], rs);// 2번째 칼럼이 들어오면 어떡할것인가,..?
    	    	}
    	    	System.out.println();
    	    }
            
        } catch (ClassNotFoundException e) { 
            System.out.println("드라이버 로드 실패");
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }
	}
	
	public static void printSelectValue(int columnType, String columnName, ResultSet rs) throws SQLException {
		
		  if (columnType == Types.NUMERIC || columnType == Types.INTEGER ) {
		    System.out.print(columnName.toUpperCase() +" : " +rs.getInt(columnName.trim()) + " ");
		  } 
		  else if (columnType == Types.CHAR || columnType == Types.VARCHAR ) {
			  System.out.print(columnName.toUpperCase() +" : "+ rs.getString(columnName.trim())+ " ");

		  } else {
		    // 그 외의 경우는 전부 String 처리
			  System.out.print(columnName.toUpperCase() +" : "+ rs.getString(columnName.trim())+ " ");
		  }
	}
	
	public static void execute(int columnType, int index, String value, PreparedStatement pstmt) throws NumberFormatException, SQLException
	{
//		System.out.println("어디서 오류냐.");
		if(value == null)
		{
			pstmt.setString(index,value);
		}
		else if (columnType == Types.NUMERIC || columnType == Types.INTEGER ) {
			pstmt.setInt(index,Integer.parseInt(value));
		} 
		else if (columnType == Types.CHAR || columnType == Types.VARCHAR ) {
			pstmt.setString(index,value);	

		} else {
			    // 그 외의 경우는 전부 String 처리
			pstmt.setString(index,value);	
		}
	}
	
	public static void tableCommand()
	{
		System.out.println("0. Return Previous Menu");
		System.out.println("1. Insert");
		System.out.println("2. Update");
		System.out.println("3. Delete");
		System.out.println("4. Select");
		System.out.print("CMD > ");
	}
	

}
