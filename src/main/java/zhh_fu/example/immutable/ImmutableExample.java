package zhh_fu.example.immutable;

import java.util.Map;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import zhh_fu.annotations.UnSafeThread;

@Slf4j
@UnSafeThread
public class ImmutableExample {
    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    private void test(final int a){
    //    a = 5;
    }

    public static void main(String[] args) {
        //a = 3;
        //map = Maps.newHashMap();
        map.put(1,3);
        log.info("{}",map.get(1));
    }
}
