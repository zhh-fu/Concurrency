package zhh_fu.example.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import zhh_fu.annotations.SafeThread;
import zhh_fu.annotations.UnSafeThread;

import java.util.Collections;
import java.util.Map;

@Slf4j
@SafeThread
public class ImmutableExample2 {

    private static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {

        //map = Maps.newHashMap();
        map.put(1,3);
        log.info("{}",map.get(1));
    }
}
