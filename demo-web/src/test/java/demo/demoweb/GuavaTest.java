package demo.demoweb;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableSet;

public class GuavaTest {
    public static void main(String[] args) {
        ImmutableSet<String> immutableSet = ImmutableSet.of("RED", "GREEN");
        HashMultiset<String> multiSet = HashMultiset.create();
        HashMultimap<String, String> map = HashMultimap.create();

    }
}
