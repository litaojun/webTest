/*    */ package com.simo.boss.aaa.simo.handler;
/*    */ 
/*    */ import com.simo.boss.aaa.request.dto.QueryUserItemResponseDto;
/*    */ import com.simo.boss.protocol.Body;
/*    */ import com.simo.boss.protocol.CommonDto;
/*    */ import com.simo.boss.protocol.handler.ICommonHandler;
/*    */ import com.simo.boss.util.IoBufferUtil;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.mina.core.buffer.IoBuffer;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ 
/*    */ public class QueryUserItemHandler
/*    */   implements ICommonHandler
/*    */ {
/* 29 */   private static final Logger log = Logger.getLogger(QueryUserItemHandler.class);
/*    */ 
/*    */   public void process(IoSession session, CommonDto commonDto)
/*    */   {
/* 46 */     CommonDto returnDto = new CommonDto();
/* 47 */     QueryUserItemResponseDto userItem = new QueryUserItemResponseDto();
/* 48 */     userItem.setPaidPlan((short)22);
/* 49 */     userItem.setUserBalance(353L);
/* 50 */     userItem.setUserFreeCallAnswer(23);
/* 51 */     userItem.setUserFreeCallDial(43);
/* 52 */     userItem.setUserFreeData(3342L);
/* 53 */     userItem.setUserFreeSmsRecv(3);
/* 54 */     userItem.setUserFreeSmsSend(34);
/* 55 */     userItem.setUserPriceCallAnswer(33);
/* 56 */     userItem.setUserPriceCallDial(35);
/* 57 */     userItem.setUserPriceData(53);
/* 58 */     userItem.setUserPriceSmsRecv(32);
/* 59 */     userItem.setUserPriceSmsSend(32);
/*    */ 
/* 61 */     Body returnBody = new Body();
/* 62 */     returnBody.setOrderCode(14);
/* 63 */     IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
/* 64 */     buffer.putUnsignedInt(0);
/*    */ 
/* 66 */     buffer.putUnsigned(userItem.getPaidPlan());
/* 67 */     buffer.putUnsignedInt(userItem.getUserBalance());
/* 68 */     buffer.putUnsignedShort(userItem.getUserFreeCallDial());
/* 69 */     buffer.putUnsignedShort(userItem.getUserFreeCallAnswer());
/* 70 */     buffer.putUnsignedShort(userItem.getUserFreeSmsSend());
/* 71 */     buffer.putUnsignedShort(userItem.getUserFreeSmsRecv());
/* 72 */     buffer.putUnsignedInt(userItem.getUserFreeData());
/* 73 */     buffer.putUnsignedShort(userItem.getUserPriceCallDial());
/* 74 */     buffer.putUnsignedShort(userItem.getUserPriceCallAnswer());
/* 75 */     buffer.putUnsignedShort(userItem.getUserPriceSmsSend());
/* 76 */     buffer.putUnsignedShort(userItem.getUserPriceSmsRecv());
/* 77 */     buffer.putUnsignedShort(userItem.getUserPriceData());
/* 78 */     buffer.flip();
/*    */ 
/* 80 */     returnBody.setSubBody(IoBufferUtil.ioBufferToByte(buffer));
/* 81 */     returnBody.countCheckCode();
/* 82 */     returnDto.setVersion((short)1);
/* 83 */     returnDto.setKeyIndex(((Long)session.getAttribute("keyIndex")).longValue());
/* 84 */     returnDto.setBodyLength(returnBody.toBytes().length);
/* 85 */     returnDto.setBody(returnBody.toBytes());
/*    */ 
/* 87 */     byte[] keys = (byte[])session.getAttribute("finalKey");
/* 88 */     if ((keys != null) && (keys.length > 0)) {
/* 89 */       returnDto.decEncryptBody(keys);
/* 90 */       log.debug("=======加密成功========");
/*    */     } else {
/* 92 */       log.error("**获取会话中的key失败,加密失败**");
/* 93 */       session.close(true);
/* 94 */       throw new RuntimeException("**获取会话中的key失败,加密失败,会话关闭**");
/*    */     }
/* 96 */     session.write(returnDto);
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.simo.handler.QueryUserItemHandler
 * JD-Core Version:    0.6.1
 */