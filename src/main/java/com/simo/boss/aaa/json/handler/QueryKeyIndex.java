/*     */ package com.simo.boss.aaa.json.handler;
/*     */ 
/*     */ import com.simo.boss.aaa.main.CommonJsonHandler;
/*     */ import com.simo.boss.common.codec.CommonJsonDto;
/*     */ import com.simo.boss.core.spring.factoryBean.SpringContextUtil;
/*     */ import com.simo.boss.domain.dto.agency.Response;
/*     */ import com.simo.boss.protocol.dao.EncryptDao;
/*     */ import com.simo.boss.protocol.dao.TerminalDao;
/*     */ import com.simo.boss.protocol.domain.Encrypt;
/*     */ import com.simo.boss.protocol.domain.Terminal;
/*     */ import com.simo.boss.util.BytesUtil;
/*     */ import com.simo.boss.util.DesUtil;
/*     */ import com.simo.boss.util.JsonUtil;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.codec.binary.Base64;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.mina.core.session.IoSession;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class QueryKeyIndex extends CommonJsonHandler
/*     */ {
/*  41 */   private static final Logger log = Logger.getLogger(QueryKeyIndex.class);
/*     */ 
/*     */   public void process(IoSession session, CommonJsonDto commonJsonDto)
/*     */   {
/*  50 */     byte[] body = commonJsonDto.getBody();
/*  51 */     String jsonString = new String(body);
/*  52 */     log.debug("=======jsonString=====>>" + jsonString);
/*  53 */     Response response = new Response();
/*  54 */     JSONObject json = null;
/*  55 */     int errorCode = 0;
/*  56 */     Map map = new HashMap();
/*     */     try {
/*  58 */       json = new JSONObject(jsonString);
/*  59 */       JSONObject paramsJson = json.getJSONObject("params");
/*  60 */       long keyIndex = Long.parseLong(paramsJson.get("keyIndex").toString());
/*     */ 
/*  62 */       EncryptDao encryptDao = (EncryptDao)SpringContextUtil.getBean("encryptDao");
/*  63 */       Encrypt encrypt = encryptDao.findById(Long.valueOf(keyIndex));
/*     */ 
/*  65 */       if (encrypt == null) {
/*  66 */         errorCode = 83886080;
/*  67 */         response.setErrorMsg("keyindex不存在");
/*  68 */         log.error("=====密钥索引无效，会话关闭=====");
/*  69 */         throw new RuntimeException("密钥索引无效，会话关闭");
/*     */       }
/*  71 */       byte[] encryptBytes = encrypt.getKeyEncrypt();
/*     */ 
/*  73 */       TerminalDao terminalDao = (TerminalDao)SpringContextUtil.getBean("terminalDao");
/*  74 */       Terminal terminal = terminalDao.findTerminalById(keyIndex);
/*  75 */       byte[] codes = terminal.getCode1().getBytes();
/*     */ 
/*  81 */       byte[] key = DesUtil.createDecryptor(codes, encryptBytes);
/*  82 */       log.debug("==key==>>" + BytesUtil.outPutToString(key));
/*  83 */       if ((key != null) && (key.length != 0)) {
/*  84 */         log.debug(BytesUtil.outPutToString(key));
/*  85 */         map.put("keyValue", Base64.encodeBase64String(key));
/*  86 */         log.debug("**获取会话中的key成功,解密成功**");
/*     */       } else {
/*  88 */         log.error("**获取会话中的key失败,解密失败**");
/*  89 */         session.close(true);
/*  90 */         throw new RuntimeException("**获取会话中的key失败,解密失败,会话关闭**");
/*     */       }
/*     */     }
/*     */     catch (JSONException e)
/*     */     {
/*  95 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  98 */     CommonJsonDto responseDto = new CommonJsonDto();
/*  99 */     response.setErrorCode(errorCode);
/* 100 */     response.setId(1L);
/* 101 */     response.setMethod("queryKeyIndex");
/* 102 */     response.setTime(new Date());
/* 103 */     response.setResult(map);
/* 104 */     String responseBody = JsonUtil.objectToJsonString(response);
/* 105 */     log.debug("============responseBody===========>>" + responseBody);
/* 106 */     responseDto.setBody(responseBody.getBytes());
/* 107 */     responseDto.setLength(responseBody.getBytes().length);
/* 108 */     session.write(responseDto);
/*     */   }
/*     */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.json.handler.QueryKeyIndex
 * JD-Core Version:    0.6.1
 */