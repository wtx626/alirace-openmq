package io.openmessaging.demo;

import io.openmessaging.KeyValue;
import io.openmessaging.Message;
import io.openmessaging.PullConsumer;

import java.util.*;

public class DefaultPullConsumer implements PullConsumer {
    private MessageStore messageStore = new MessageStore();
    private KeyValue properties;
    private String queue;
    private Set<String> buckets = new HashSet<>();
    private List<String> bucketList = new LinkedList<>();

    private int lastIndex = 0;

    public DefaultPullConsumer(KeyValue properties) {
        this.properties = properties;
        MessageDrawer messageDrawer = MessageDrawer.getInstance();
        LinkedList<Message> messages = messageDrawer.loadFromDisk(properties.getString("STORE_PATH"));
        for (Message message : messages) {
            messageStore.putMessage(message);
        }
    }

    @Override public KeyValue properties() {
        return properties;
    }


    @Override public synchronized Message poll() {
        if (buckets.size() == 0 || queue == null) {
            return null;
        }

        for (int i = 0; i < bucketList.size(); i++) {
            Message message = messageStore.pullMessage(queue, bucketList.get(i));
            if (message != null) {
                return message;
            }
        }
        return null;
    }

    @Override public Message poll(KeyValue properties) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override public void ack(String messageId) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override public void ack(String messageId, KeyValue properties) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override public synchronized void attachQueue(String queueName, Collection<String> topics) {
        // one thread can only attach one queue while can attach all topics.
        // according to the rule, different consumer won't attach to the same queue
        if (queue != null && !queue.equals(queueName)) {
            throw new ClientOMSException("You have alreadly attached to a queue " + queue);
        }
        String str = "";
        for (String topic:topics) {
            str += topic + ",";
        }
        System.out.println(this.toString() + " attachQueue: " + queueName + " topics: [" + str + "]");
        queue = queueName;
        buckets.add(queueName);
        buckets.addAll(topics);
        bucketList.clear();
        bucketList.addAll(buckets);
    }


}
