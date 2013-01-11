package com.xegaming.uedit;

import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * User: Benjamin
 * Date: 18/07/12
 * Time: 13:53
 */
class BlockQueue extends Thread {
    final LinkedList<QueuedBlock> list = new LinkedList<QueuedBlock>();
    private final uEdit threadit;
    boolean shouldrun = true;

    public BlockQueue(uEdit threadit) {
        this.threadit = threadit;
        this.setName("threadit-BlockQueue");
    }

    @Override
    public void run() {
        while (shouldrun) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (list) {
              
                if (list.size() > 0) {
                    uEdit.log.info("Queue loaded : " + list.size() + " remaining.");
                }
                int sleeptime = 0;
                while (!list.isEmpty()) {
                    try {
                        final QueuedBlock queuedBlock = list.pop();
                       
                        threadit.getServer().getScheduler().scheduleSyncDelayedTask(threadit, new Runnable() {
                            public void run() {
                                World w = threadit.getServer().getWorld(queuedBlock.worldName);
                                if (w == null) {
                                    return;
                                }
                                final Block b = w.getBlockAt(queuedBlock.X, queuedBlock.Y, queuedBlock.Z);
                                if (b == null) {
                                    return;
                                }

                                if (!b.getChunk().isLoaded()) {
                                    b.getChunk().load();
                                }
                                b.setTypeId(queuedBlock.newID);
                                
                            }
                        });
                        sleeptime++;
                        if(sleeptime>=500){
                        	try {
								sleep(5);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
                        	sleeptime = 0;
                        }
                    } catch (NoSuchElementException e) {
                        return;
                    }
                    
                }
            }


        }
    }

    public void addToBlockQueue(ArrayList<QueuedBlock> queuedBlockArrayList) {
        synchronized (list) {
            list.addAll(queuedBlockArrayList);
        }
    }
}
