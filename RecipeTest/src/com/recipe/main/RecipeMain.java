package com.recipe.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.recipe.common.ObjectUtils;
import com.recipe.jsonStream.ConnectRCP;
import com.recipe.vo.RCP_CrseVO;
import com.recipe.vo.RCP_InfoVO;
import com.recipe.vo.RCP_IrdntVO;

public class RecipeMain {
	
	private static ObjectUtils objUtils = new ObjectUtils();
	private static final Logger logger = LoggerFactory.getLogger(RecipeMain.class);
	
	private static Scanner sc;
	
	private static ConnectRCP cRcp = new ConnectRCP();
	
	public static void main(String[] args) {
		
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
			new RecipeMain().taskIrdnt();
		} else if ("2".equals(task) || "레시피".equals(task)) {
			new RecipeMain().taskCrse();
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
