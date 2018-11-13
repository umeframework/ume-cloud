//package org.umeframework.cloud.controller;
//
//import java.util.Date;
//import java.util.Queue;
//import java.util.concurrent.LinkedBlockingQueue;
//
///**
// * ServiceInvokeStatistics
// * 
// * @author Yue MA
// */
//public class ServiceInvokeStatistics {
//	/**
//	 * total invoke count
//	 */
//	private long count;
//	/**
//	 * max invoke time
//	 */
//	private long maxTime;
//	/**
//	 * totalTime
//	 */
//	private long totalTime;
//	/**
//	 * lastArchiveTime
//	 */
//	private Date lastArchiveTime;
//	/**
//	 * archives
//	 */
//	private Queue<ServiceInvokeStatistics> archives = new LinkedBlockingQueue<ServiceInvokeStatistics>();
//	/**
//	 * maxArchiveSize
//	 */
//	private int maxArchiveSize = 5;
//
//	/**
//	 * Statistics
//	 */
//	public ServiceInvokeStatistics() {
//	}
//
//	/**
//	 * Statistics
//	 * 
//	 * @param count
//	 * @param maxTime
//	 * @param totalTime
//	 * @param lastResetTime
//	 */
//	public ServiceInvokeStatistics(long count, long maxTime, long totalTime, Date lastArchiveTime) {
//		this.count = count;
//		this.maxTime = maxTime;
//		this.totalTime = totalTime;
//		this.lastArchiveTime = lastArchiveTime;
//	}
//
//	/**
//	 * getAveTime
//	 * 
//	 * @return
//	 */
//	public long getAveTime() {
//		return count > 0 ? totalTime / count : 0;
//	}
//
//	/**
//	 * update
//	 */
//	public void update(long useTime) {
//		if (Long.MAX_VALUE - useTime <= totalTime) {
//			if (archives.size() >= maxArchiveSize) {
//				archives.remove();
//			}
//			archives.add(new ServiceInvokeStatistics(count, maxTime, totalTime, lastArchiveTime));
//			count = 0;
//			maxTime = 0;
//			totalTime = 0;
//			lastArchiveTime = new Date(System.currentTimeMillis());
//		} else {
//			totalTime = totalTime + useTime;
//			count = count + 1;
//			if (useTime > maxTime) {
//				maxTime = useTime;
//			}
//		}
//	}
//
//	/**
//	 * @return the count
//	 */
//	public long getCount() {
//		return count;
//	}
//
//	/**
//	 * @return the maxTime
//	 */
//	public long getMaxTime() {
//		return maxTime;
//	}
//
//	/**
//	 * @return the lastArchiveTime
//	 */
//	public Date getLastArchiveTime() {
//		return lastArchiveTime;
//	}
//	
//	/* (non-Javadoc)
//	 * @see java.lang.Object#toString()
//	 */
//	public String toString() {
//		StringBuilder str = new StringBuilder();
//		str.append("{");
//		str.append("\"count\":");
//		str.append(this.getCount());
//		str.append(",\"aveTime\":");
//		str.append(this.getAveTime());
//		str.append(",\"maxTime\":");
//		str.append(this.getMaxTime());
//		str.append(",\"archives\":");
//		str.append(archives.size());
//		str.append("}");
//		return str.toString();
//	}
//}
