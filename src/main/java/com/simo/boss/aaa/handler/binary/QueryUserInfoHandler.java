/*    */ package com.simo.boss.aaa.handler.binary;
/*    */ 
/*    */ import com.simo.boss.aaa.request.dto.QueryUserInfoRequestDto;
/*    */ import com.simo.boss.aaa.service.UserInfoFacade;
/*    */ import com.simo.boss.domain.dto.BaseResp;
/*    */ import com.simo.boss.domain.dto.agency.Response;
/*    */ import com.simo.boss.protocol.handler.BaseMessageHandler;
/*    */ import com.simo.boss.util.IoBufferUtil;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Set;
/*    */ import org.apache.mina.core.buffer.IoBuffer;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component("queryUserHandler")
/*    */ public class QueryUserInfoHandler extends BaseMessageHandler
/*    */ {
/* 22 */   private static final Logger log = LoggerFactory.getLogger(QueryUserInfoHandler.class);
/*    */ 
/*    */   @Autowired
/*    */   UserInfoFacade userInfoFacade;
/*    */ 
/*    */   protected Response<BaseResp> process(byte[] bytes) {
/* 29 */     QueryUserInfoRequestDto userInfoRequestDto = new QueryUserInfoRequestDto(bytes);
/* 30 */     Map map = this.userInfoFacade.queryUserInfo(userInfoRequestDto);
/* 31 */     log.debug("map====>>" + map);
/*    */ 
/* 33 */     final byte[] responseBody = wrapUserInfo(map);
/* 34 */     Response resp = new Response();
/* 35 */     resp.setResult(new BaseResp()
/*    */     {
/*    */       public byte[] toBytes() {
/* 38 */         return responseBody;
/*    */       }
/*    */     });
/* 41 */     return resp;
/*    */   }
/*    */ 
/*    */   private byte[] wrapUserInfo(Map<String, String> map)
/*    */   {
/* 46 */     IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
/*    */ 
/* 48 */     int itemCount = map.size();
/* 49 */     log.debug("itemCount====>>" + itemCount);
/*    */ 
/* 51 */     buffer.putUnsigned(itemCount);
/* 52 */     Set set = map.entrySet();
/* 53 */     if (null != set) {
/* 54 */       Iterator iterator = set.iterator();
/* 55 */       while (iterator.hasNext()) {
/* 56 */         Map.Entry entry = (Map.Entry)iterator.next();
/* 57 */         Short key = Short.valueOf(Short.parseShort(entry.getKey().toString()));
/* 58 */         log.debug("key====>>" + key);
/* 59 */         String value = entry.getValue().toString();
/* 60 */         log.debug("value==>>" + value);
/* 61 */         IoBuffer tempBuffer = wrapData(key, value);
/* 62 */         buffer.put(tempBuffer);
/*    */       }
/*    */     }
/* 65 */     return IoBufferUtil.ioBufferToByte(buffer.flip());
/*    */   }
/*    */ 
/*    */   private IoBuffer wrapData(Short key, String value)
/*    */   {
/* 70 */     IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
/*    */ 
/* 72 */     if ((key.shortValue() == 1) || (key.shortValue() == 2)) {
/* 73 */       byte[] valueBytes = value.getBytes();
/*    */ 
/* 75 */       buffer.putUnsignedShort(2 + valueBytes.length);
/*    */ 
/* 77 */       buffer.putUnsigned(key.shortValue());
/*    */ 
/* 79 */       buffer.putUnsigned(valueBytes.length);
/* 80 */       buffer.put(valueBytes);
/* 81 */     } else if (key.shortValue() == 3) {
/* 82 */       byte[] valueBytes = value.getBytes();
/*    */ 
/* 84 */       buffer.putUnsignedShort(3 + valueBytes.length);
/*    */ 
/* 86 */       buffer.putUnsigned(key.shortValue());
/*    */ 
/* 88 */       buffer.putUnsignedShort(valueBytes.length);
/* 89 */       buffer.put(valueBytes);
/*    */     }
/* 91 */     return buffer.flip();
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.handler.binary.QueryUserInfoHandler
 * JD-Core Version:    0.6.1
 */