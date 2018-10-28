package com.microhard.ga;

import com.microhard.ga.models.Me;
import com.microhard.ga.models.Receiver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicDB {

    public static List<Receiver> myPeers = new ArrayList<>();

    public static Me me = new Me("750879", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsicmViLWN1c3RvbWVyLXNlcnZpY2UiXSwidXNlcl9uYW1lIjoiTUIxQk1PNlIiLCJpZGVudGl0eSI6eyJvcmdfdW5pdCI6Im91PTk0NixvPVNHQi1CQU5LLGRjPUFDUCxkYz11ZmUsZGM9Y29tIiwicGVyc29uX2lkIjoiMzI4MDU5Iiwicm9sZSI6IkNVU1RPTUVSIiwiYWNjZXNzX3Byb2ZpbGVfaWQiOiIyMjY3In0sInNjb3BlIjpbIndyaXRlIiwicmVhZCJdLCJpc3MiOiJpc3N1ZXIiLCJqdGkiOiJlYmY0ZGZmYS00ZWU2LTQ4NGUtODdiMy1hYzc5MDk3ZWRmZjUiLCJjbGllbnRfaWQiOiJyZWJSZXRhaWwiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXX0.BDG474G1M7FDdGRxOAVxXYhplnoWgr0YSn6WEVOhg6Y", "328059", "2267");

    public BasicDB() {
        myPeers.addAll(Arrays.asList(new Receiver("John", "Smith", "62144011984535834566199945", new String[]{"Baker Street 12, London"}), new Receiver("Adam","Sandler", "93109010690461142779799926", new String[]{"His Street 1, Dallas"}), new Receiver("Donald", "Trump", "23193011909596126736949723", new String[]{"White House 100, Washington"}), new Receiver("Bill", "Gates", "80875710376197287396559942", new String[]{"Scam Street 0, Scam City"})));
    }



}
