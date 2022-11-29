package com.rabbitmq.demo.publisher;

import com.rabbitmq.demo.config.MessagingConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@RestController
@RequestMapping("/rabbitmq")
public class BatchUpload {

    @Value("${batch.file.path}")
    private String batchFileLoc;

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/batch/start")
    public String batchIngest() throws IOException {
        long start2 = System.currentTimeMillis();
        File file = new File(batchFileLoc);

        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            br.close();
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Elapsed Time in milli seconds: "+ (end2-start2));
        return "Success !!";
    }
}
