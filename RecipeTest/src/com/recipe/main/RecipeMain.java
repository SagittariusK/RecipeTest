package com.recipe.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.recipe.common.ConnectionOra;
import com.recipe.common.ObjectUtils;
import com.recipe.jsonStream.ConnectRCP;
import com.recipe.vo.RCP_CrseVO;
import com.recipe.vo.RCP_InfoVO;
import com.recipe.vo.RCP_IrdntVO;
import com.recipe.vo.Recipe_InfoVO;

public class RecipeMain {
	
	private static ObjectUtils objUtils = new ObjectUtils();
	private static final Logger logger = LoggerFactory.getLogger(RecipeMain.class);
	
	private static Scanner sc;
	
	private static ConnectRCP cRcp = new ConnectRCP();
	
	public static void main(String[] args) {
		
		Connection conn = null; // DB연결된 상태(세션)을 담은 객체
        PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
        ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
        
        // SQL 문장을 만들고 만약 문장이 질의어(SELECT문)라면
        // 그 결과를 담을 ResulSet 객체를 준비한 후 실행시킨다.
        
        List<Recipe_InfoVO> recipe_InfoList = new ArrayList<Recipe_InfoVO>();
        Recipe_InfoVO recipe_InfoVo = new Recipe_InfoVO();
        
        try {
        	String quary = "SELECT * FROM RECIPE_INFO";
        	
        	conn = ConnectionOra.getConnection();
        	pstm = conn.prepareStatement(quary);
        	rs = pstm.executeQuery();
        	
        	System.out.println("결과 출력시작");
			while(rs.next()){
				//숫자 대신 컬럼 이름을 적어도 된다.
//				int recipe_code = rs.getInt(1);
//				String recipe_name = rs.getString(2);
//				String recipe_summ = rs.getString(3);
//				int recipe_price = rs.getInt(4);
//				String recipe_process = rs.getString(6);
//				String recipe_status = rs.getString(7);
//				String rd_id = rs.getString(8);
//				java.sql.Date hiredate = rs.getDate(5); // Date 타입 처리
				
//				String result = recipe_code+" "+recipe_name+" "+recipe_summ+" "+recipe_price+" "+recipe_process+" "+recipe_status+" "+rd_id;
//				System.out.println(result);
				
				recipe_InfoVo = new Recipe_InfoVO();
				recipe_InfoVo.setRecipe_code(rs.getInt(1));
				recipe_InfoVo.setRecipe_name(rs.getString(2));
				recipe_InfoVo.setRecipe_summ(rs.getString(3));
				recipe_InfoVo.setRecipe_price(rs.getInt(4));
				recipe_InfoVo.setRecipe_process(rs.getString(5));
				recipe_InfoVo.setRecipe_status(rs.getString(6));
				recipe_InfoVo.setRd_id(rs.getString(7));
				
				// 위 아래 같은 역할이며 위에처럼해도 되고 아래처럼 해도 된다.
				recipe_InfoVo = new Recipe_InfoVO();
				recipe_InfoVo.setRecipe_code(rs.getInt("RECIPE_CODE"));
				recipe_InfoVo.setRecipe_name(rs.getString("RECIPE_NAME"));
				recipe_InfoVo.setRecipe_summ(rs.getString("RECIPE_SUMM"));
				recipe_InfoVo.setRecipe_price(rs.getInt("RECIPE_PRICE"));
				recipe_InfoVo.setRecipe_process(rs.getString("RECIPE_PROCESS"));
				recipe_InfoVo.setRecipe_status(rs.getString("RECIPE_STATUS"));
				recipe_InfoVo.setRd_id(rs.getString("RD_ID"));
				
				recipe_InfoList.add(recipe_InfoVo);
			}
			
			for (Recipe_InfoVO recipe_InfoVO2 : recipe_InfoList) {
				System.out.println("recipe_InfoVO2: " + recipe_InfoVO2.toString());
			}

		} catch (SQLException sqle) {
            System.out.println("SELECT문에서 예외 발생");
            sqle.printStackTrace();
		}finally{
            // DB 연결을 종료한다.
            try{
                if ( rs != null ){rs.close();}   
                if ( pstm != null ){pstm.close();}   
                if ( conn != null ){conn.close(); }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
            
        }

		String task = "1";
		sc = new Scanner(System.in);
//		try {
//			System.out.println("작업을 선택하세요.:");
//			System.out.println("1.재료명, 2.레시피명");
//			System.out.print("입력: ");
//			task = sc.nextLine();
//		} catch (Exception e) {
//			logger.error("task Select Failure:");
//			new CatchUtils(e);
//		}
		task = objUtils.toStr(task).replace(".", "").replace("명", "").trim();
		
		if("1".equals(task) || "재료".equals(task)) {
//			new RecipeMcain().taskIrdnt();
		} else if ("2".equals(task) || "레시피".equals(task)) {
//			new RecipeMain().taskCrse();
		} else {
//			new RecipeMain().taskInfo();
		}
		
		
	}
	
	public List<RCP_InfoVO> taskInfo(String recipe_nm) {
		sc = new Scanner(System.in);
		
		HashMap<String, Object> paramHashMap = new HashMap<String, Object>();
		paramHashMap.put("recipe_nm", objUtils.toStr("recipe_nm"));
		
		// API URL 호출
		HashMap<String, Object> resultHashMap = cRcp.getInfo(1, 999, paramHashMap);
		
		List<RCP_InfoVO> infoList = cRcp.toListInInfoVo(resultHashMap);
		String code = objUtils.toStr(cRcp.toResultHashMap(resultHashMap).get("code"));
		String message = objUtils.toStr(cRcp.toResultHashMap(resultHashMap).get("message"));
		
		if("INFO-000".equals(code) && "정상 처리되었습니다.".equals(message) && 0 < infoList.size()) {
//			logger.info(message);
//			for (RCP_InfoVO rcp_InfoVo : infoList) {
//				System.out.println("최종: " + rcp_InfoVo);
//			}
		} else if("INFO-000".equals(code) && "정상 처리되었습니다.".equals(message) && 1 > infoList.size()) {
//			logger.info("검색 결과가 없습니다.");
		}
		
		return infoList;
	}
	
	public void taskIrdnt() {
		sc = new Scanner(System.in);
		String task = "";
//		try {
//			System.out.println("원하는 재료명을 입력하세요:");
//			System.out.print("입력: ");
//			task = sc.nextLine();
//		} catch (Exception e) {
//			logger.error("task Irdnt Select Failure:");
//			new CatchUtils(e);
//		}
		
		List<RCP_InfoVO> infoList = new RecipeMain().taskInfo("");
		
//		String irdnt_nm = task;
		String irdnt_nm = "쌀";
		HashMap<String, Object> paramHashMap = new HashMap<String, Object>();
		paramHashMap.put("irdnt_nm", irdnt_nm);
		
		// API URL 호출
		HashMap<String, Object> resultHashMap = cRcp.getIrdnt(1, 999, paramHashMap);
		
		List<RCP_IrdntVO> irdntList = cRcp.toListInIrdntVo(resultHashMap);
		String code = objUtils.toStr(cRcp.toResultHashMap(resultHashMap).get("code"));
		String message = objUtils.toStr(cRcp.toResultHashMap(resultHashMap).get("message"));
		
		if("INFO-000".equals(code) && "정상 처리되었습니다.".equals(message) && 0 < irdntList.size()) {
			logger.info(message);
			for (RCP_IrdntVO rcp_IrdntVo : irdntList) {
				String rcpID_Irdnt = objUtils.toStr(rcp_IrdntVo.getRecipe_id());
				for (RCP_InfoVO rcp_InfoVo : infoList) {
					String rcpID_Info = objUtils.toStr(rcp_InfoVo.getRecipe_id());
					if(rcpID_Irdnt.equals(rcpID_Info)) {
						
					}
				}
			}
		} else if("INFO-000".equals(code) && "정상 처리되었습니다.".equals(message) && 1 > irdntList.size()) {
			logger.info("검색 결과가 없습니다.");
		}
	}
	
	public void taskCrse() {
		sc = new Scanner(System.in);
		String task = "";
//		try {
//			System.out.println("원하는 레시피명을 입력하세요:");
//			System.out.print("입력: ");
//			task = sc.nextLine();
//		} catch (Exception e) {
//			logger.error("task Crse Select Failure:");
//			new CatchUtils(e);
//		}
		
//		String recipe_id = task;
		List<RCP_InfoVO> infoList = new RecipeMain().taskInfo(task);
		
		int recipe_id = infoList.get(0).getRecipe_id();
		
		if("".equals(task) || (null != infoList && 0 < infoList.size())) {
			HashMap<String, Object> paramHashMap = new HashMap<String, Object>();
			paramHashMap.put("recipe_id", recipe_id);
			
			// API URL 호출
			HashMap<String, Object> resultHashMap = cRcp.getCrse(1, 999, paramHashMap);
			
			List<RCP_CrseVO> crseList = cRcp.toListInCrseVo(resultHashMap);
			String code = objUtils.toStr(cRcp.toResultHashMap(resultHashMap).get("code"));
			String message = objUtils.toStr(cRcp.toResultHashMap(resultHashMap).get("message"));
			
			if("INFO-000".equals(code) && "정상 처리되었습니다.".equals(message) && 0 < crseList.size()) {
				logger.info(message);
				for (RCP_CrseVO rcp_CrseVo : crseList) {
					System.out.println("최종: " + rcp_CrseVo);
				}
			} else if("INFO-000".equals(code) && "정상 처리되었습니다.".equals(message) && 1 > crseList.size()) {
				logger.info("검색 결과가 없습니다.");
			}
		}else {
			logger.info("검색 결과가 없습니다.");
		}
	}
	
	

}
