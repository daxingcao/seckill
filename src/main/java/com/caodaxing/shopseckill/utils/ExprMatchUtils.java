package com.caodaxing.shopseckill.utils;

import com.alibaba.druid.util.StringUtils;

import javax.script.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 逻辑判断表达式判断工具类
 * @author daxing.cao
 *
 */
public class ExprMatchUtils {
	
	private static final ScriptEngineManager MANAGER = new ScriptEngineManager();
	private static ThreadLocal<ScriptEngine> threadLocal = new ThreadLocal<>();
	
	/**
	 * 将逻辑表达式中的参数放入ScriptEngine对象中
	 * @param params
	 */
	public static void setMatchParam(Map<String, Object> params) {
		ScriptEngine engine = getScriptEngine();
		Set<Entry<String, Object>> entrySet = params.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			Object obj = entry.getValue();
			Double value = null;
			if(obj != null && !StringUtils.isEmpty(obj.toString())
					&& StringUtil.isNumber(obj.toString())) {
				value = Double.valueOf(obj.toString());
			}else {
				continue;
			}
			engine.put(entry.getKey(), value);
		}
	}
	
	/**
	 * 将逻辑表达式中的参数放入ScriptEngine对象中
	 * @param params 设置参数
	 * @param clear 是否清除以前参数
	 */
	public static void setMatchParam(Map<String, Object> params,boolean clear) {
		if(clear) {
			ExprMatchUtils.clear();
		}
		setMatchParam(params);
	}
	
	/**
	 * 验证逻辑表达式是否成立
	 * 使用该方法前,请确认逻辑表达式中的参数已经放入ScriptEngine对象中
	 * 可使用ScriptEngine()方法放入参数
	 * @param expr
	 * @return
	 * @throws ScriptException
	 */
	public static boolean verifyExpr(String expr) throws ScriptException {
		ScriptEngine engine = getScriptEngine();
		if(StringUtils.isEmpty(expr)) {
			return true;
		}
		return (boolean)engine.eval(expr);
	}
	
	/**
	 * 传入表达式参数和表达式,验证逻辑表达式是否成立
	 * @param params
	 * @param expr
	 * @return boolean
	 * @throws {@link ScriptException}
	 */
	public static boolean verifyExpr(Map<String, Object> params,String expr) throws ScriptException {
		setMatchParam(params);
		return verifyExpr(expr);
	}
	
	public static void clear() {
		ScriptEngine engine = getScriptEngine();
		Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
		bindings.clear();
		threadLocal.remove();
	}
	
	private static ScriptEngine getScriptEngine() {
		ScriptEngine engine = threadLocal.get();
		if(engine == null) {
			engine = MANAGER.getEngineByName("javascript");
			threadLocal.set(engine);
		}
		return threadLocal.get();
	}
	
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("aa", 3000);
		map.put("bb", 7000);
		setMatchParam(map);
		try {
			boolean verifyExpr = verifyExpr("(aa-bb)>3000||(bb-aa)>3000");
			System.out.println(verifyExpr);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
