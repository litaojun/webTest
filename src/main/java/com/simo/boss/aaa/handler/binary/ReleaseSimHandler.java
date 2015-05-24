/*    */ package com.simo.boss.aaa.handler.binary;
/*    */ 
/*    */ import com.simo.boss.aaa.request.dto.ReleaseSimRequestDto;
/*    */ import com.simo.boss.aaa.service.SimInfoFacade;


/*    */ import com.simo.boss.domain.dto.BaseResp;
/*    */ import com.simo.boss.domain.dto.agency.Response;
/*    */ import com.simo.boss.enumerate.Constant.ReleaseReason;
/*    */ import com.simo.boss.protocol.handler.BaseMessageHandler;
import com.simo.boss.util.Constant;

/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component("releaseSimHandler")
/*    */ public class ReleaseSimHandler extends BaseMessageHandler
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private SimInfoFacade simInfoFacade;
/* 28 */   private static final Logger log = LoggerFactory.getLogger(ReleaseSimHandler.class);
/*    */ 
/*    */   public Response<BaseResp> process(byte[] bytes)
/*    */   {
/* 33 */     log.debug("ReleaseSimHandler processes start ....");
/* 34 */     ReleaseSimRequestDto dto = new ReleaseSimRequestDto(bytes);
/*    */ 
/* 36 */     this.simInfoFacade.releaseSim(dto, Constant.ReleaseReason.ByTerminal.getValue());
/* 37 */     log.debug("ReleaseSimHandler processes end ....");
/*    */ 
/* 40 */     Response resp = new Response();
/* 41 */     resp.setResult(new BaseResp()
/*    */     {
/*    */       public byte[] toBytes()
/*    */       {
/* 45 */         return new byte[0];
/*    */       }
/*    */     });
/* 48 */     return resp;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.handler.binary.ReleaseSimHandler
 * JD-Core Version:    0.6.1
 */