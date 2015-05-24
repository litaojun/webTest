/*    */ package com.simo.boss.aaa.agency.handler;
/*    */ 
/*    */ import com.simo.boss.common.codec.CommonJsonProtocolCodecFactory;
/*    */ import java.net.InetSocketAddress;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
/*    */ import org.apache.mina.core.future.CloseFuture;
/*    */ import org.apache.mina.core.future.ConnectFuture;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ import org.apache.mina.core.session.IoSessionConfig;
/*    */ import org.apache.mina.filter.codec.ProtocolCodecFilter;
/*    */ import org.apache.mina.transport.socket.SocketSessionConfig;
/*    */ import org.apache.mina.transport.socket.nio.NioSocketConnector;
/*    */ 
/*    */ public class AgencyFactory<T>
/*    */ {
/* 28 */   private static final Logger log = Logger.getLogger(AgencyFactory.class);
/*    */   private CommonAgencyHandler<T> handler;
/*    */ 
/*    */   public AgencyFactory()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CommonAgencyHandler<T> getHandler()
/*    */   {
/* 35 */     return this.handler;
/*    */   }
/*    */   public void setHandler(CommonAgencyHandler<T> handler) {
/* 38 */     this.handler = handler;
/*    */   }
/*    */   public AgencyFactory(CommonAgencyHandler<T> handler) {
/* 41 */     this.handler = handler;
/*    */   }
/*    */ 
/*    */   public void handler() {
/* 45 */     NioSocketConnector connector = new NioSocketConnector();
/* 46 */     connector.getSessionConfig().setReadBufferSize(10240);
/* 47 */     connector.getFilterChain().addLast("codecFilter", new ProtocolCodecFilter(new CommonJsonProtocolCodecFactory()));
/*    */ 
/* 49 */     connector.setHandler(this.handler);
/* 50 */     IoSession session = null;
/*    */     try {
/* 52 */       ConnectFuture future = connector.connect(new InetSocketAddress(System.getProperty("agencyServer.ip").toString(), Integer.parseInt(System.getProperty("agencyServer.port").toString())));
/* 53 */       future.awaitUninterruptibly();
/* 54 */       session = future.getSession();
/* 55 */       log.debug("=======AgencyFactory连接关闭=========");
/* 56 */       session.getConfig().setUseReadOperation(true);
/* 57 */       session.getCloseFuture().awaitUninterruptibly();
/*    */     } catch (Exception e) {
/* 59 */       e.printStackTrace();
/* 60 */       log.error("===连接Agency服务器失败!!!");
/*    */     }
/* 62 */     connector.dispose(true);
/* 63 */     log.debug("=======connector.dispose()=========");
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.agency.handler.AgencyFactory
 * JD-Core Version:    0.6.1
 */