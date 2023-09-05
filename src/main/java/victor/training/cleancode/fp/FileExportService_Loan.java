package victor.training.cleancode.fp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Unchecked;
import victor.training.cleancode.fp.support.OrderRepo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
public class FileExportService_Loan {
   private final OrderRepo orderRepo;

   public void exportOrders() throws IOException {
      File file = new File("target/orders.csv");
      log.info("Starting export into {} ...", file.getAbsolutePath());
      long t0 = System.currentTimeMillis();
      try (Writer writer = new FileWriter(file)) {

         writer.write("order_id;date\n");
         orderRepo.findByActiveTrue()
             .map(o -> o.getId() + ";" + o.getCreationDate() + "\n")
//             .forEach(rearunceRuntime(writer::write));
             .forEach(Unchecked.consumer(writer::write)); //jooq library
         //  <dependency>
         //            <groupId>org.jooq</groupId>
         //            <artifactId>jool</artifactId>
         //            <version>0.9.14</version>
         //        </dependency>
         log.info("Export DONE");
      } catch (Exception e) {
         log.error("Export FAILED!", e); // TERROR-Driven Development
         // imagine... sendErrorEmail(e);
         throw e;
      } finally {
         long t1 = System.currentTimeMillis();
         log.info("Export completed in {} seconds ", (t1 - t0) / 1000);
      }
   }

   public interface ConsumeruMeu<T> {
      void accept(T t) throws Exception;
   }

   public static <T> Consumer<T> rearunceRuntime(ConsumeruMeu<T> f) {
     return  x -> {
         try {
            f.accept(x);
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      };
   }
   
}

