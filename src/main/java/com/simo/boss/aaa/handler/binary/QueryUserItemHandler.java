/*    */ package com.simo.boss.aaa.handler.binary;
/*    */ 
/*    */ import com.simo.boss.aaa.request.dto.QueryUserItemResponseDto;
/*    */ import com.simo.boss.domain.dto.BaseResp;
/*    */ import com.simo.boss.domain.dto.agency.Response;
/*    */ import com.simo.boss.protocol.handler.BaseMessageHandler;
/*    */ import com.simo.boss.util.IoBufferUtil;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.mina.core.buffer.IoBuffer;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component("queryUserItemHandler")
/*    */ public class QueryUserItemHandler extends BaseMessageHandler
/*    */ {
/* 24 */   private static final Logger log = Logger.getLogger(QueryUserItemHandler.class);
/*    */ 
/*    */   public Response<BaseResp> process(IoSession session, byte[] bytes)
/*    */   {
/* 33 */     log.debug("get user item");
/* 34 */     QueryUserItemResponseDto userItem = getUserItem();
/*    */ 
/* 36 */     IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
/*    */ 
/* 39 */     buffer.putUnsigned(userItem.getPaidPlan());
/* 40 */     buffer.putUnsignedInt(userItem.getUserBalance());
/* 41 */     buffer.putUnsignedShort(userItem.getUserFreeCallDial());
/* 42 */     buffer.putUnsignedShort(userItem.getUserFreeCallAnswer());
/* 43 */     buffer.putUnsignedShort(userItem.getUserFreeSmsSend());
/* 44 */     buffer.putUnsignedShort(userItem.getUserFreeSmsRecv());
/* 45 */     buffer.putUnsignedInt(userItem.getUserFreeData());
/* 46 */     buffer.putUnsignedShort(userItem.getUserPriceCallDial());
/* 47 */     buffer.putUnsignedShort(userItem.getUserPriceCallAnswer());
/* 48 */     buffer.putUnsignedShort(userItem.getUserPriceSmsSend());
/* 49 */     buffer.putUnsignedShort(userItem.getUserPriceSmsRecv());
/* 50 */     buffer.putUnsignedShort(userItem.getUserPriceData());
/* 51 */     buffer.flip();
/*    */ 
/* 53 */     final byte[] respBytes = IoBufferUtil.ioBufferToByte(buffer);
/* 54 */     buffer.free();
/* 55 */     Response resp = new Response();
/* 56 */     resp.setResult(new BaseResp()
/*    */     {
/*    */       public byte[] toBytes()
/*    */       {
/* 60 */         return respBytes;
/*    */       }
/*    */     });
/* 63 */     return resp;
/*    */   }
/*    */ 
/*    */   private QueryUserItemResponseDto getUserItem()
/*    */   {
/* 68 */     QueryUserItemResponseDto userItem = new QueryUserItemResponseDto();
/* 69 */     userItem.setPaidPlan((short)22);
/* 70 */     userItem.setUserBalance(353L);
/* 71 */     userItem.setUserFreeCallAnswer(23);
/* 72 */     userItem.setUserFreeCallDial(43);
/* 73 */     userItem.setUserFreeData(3342L);
/* 74 */     userItem.setUserFreeSmsRecv(3);
/* 75 */     userItem.setUserFreeSmsSend(34);
/* 76 */     userItem.setUserPriceCallAnswer(33);
/* 77 */     userItem.setUserPriceCallDial(35);
/* 78 */     userItem.setUserPriceData(53);
/* 79 */     userItem.setUserPriceSmsRecv(32);
/* 80 */     userItem.setUserPriceSmsSend(32);
/* 81 */     return userItem;
/*    */   }
/*    */ 
/*    */   protected Response<BaseResp> process(byte[] bytes)
/*    */   {
/* 86 */     return null;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.handler.binary.QueryUserItemHandler
 * JD-Core Version:    0.6.1
 */