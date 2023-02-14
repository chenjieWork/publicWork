package com.example.demo22;

import org.springframework.boot.SpringApplication;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 13096 on 2022/11/7.
 */
public class redFile {

  /*  public static void main(String[] args) {
        List<String> pathList = new ArrayList<String>();
        pathList.add("D:/测试");
       // pathList.add("D:/测试/22");
        Map<String , Object > ajaxResult = main( pathList);
        System.out.println(ajaxResult);
    }

    public static  Map<String , Object > main(List<String> pathList){
        Map<String , Object > ajaxResult = new HashMap<String , Object >();
        ArrayList<String> fileNameList = new ArrayList<String>();
        if(pathList.size() > 0){
            for(String path : pathList){
                File file = new File(path);
                if(!file.exists()){   //判断文件路径是否正确 （包含windouw环境和linux环境）
                    ajaxResult.put("false","路径错误");
                    return ajaxResult;
                }
                fileNameList =  reddirbypath(path,fileNameList);
                List<Menu>  MenuList =  getFilePathTree(fileNameList);
                ajaxResult.put("true",fileNameList);
            }
        }else{
            ajaxResult.put("false","路径错误");
        }
        return ajaxResult;
    }


    public  static ArrayList<String>  reddirbypath(String path,ArrayList<String> fileNameList){
        File file = new File(path);
        if(file.isDirectory()){ //如果是目录的话，递归调用这个查询方法。
            File []files=file.listFiles();
            for (File file2 : files) {
                if(file2.isDirectory()){
                    fileNameList.add(file2.toString());
                    reddirbypath(file2.toString(),fileNameList);
                }
            }
        }
        return fileNameList;

    }



    public static List<Menu> getFilePathTree(List<String> paths) {
        Map<String, Integer> map = new LinkedHashMap<>();
        Integer id = 1;
        for (int i = 0; i < paths.size(); i++) {
            String newPath = paths.get(i).replaceAll("\\\\", "/");
            String[] path = newPath.split("/");
            String p = "";
            for (int j = 0; j < path.length; j++) {
                p += path[j] + "/";
                if (!map.containsKey(p.substring(0, p.length() - 1))) {
                    map.put(p.substring(0, p.length() - 1), id++);
                }
            }
        }

        List<Menu> menus = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Menu menu = new Menu();
            Integer values = entry.getValue();
            String[] keys = entry.getKey().split("/");
            menu.setId(values);
            if (keys.length == 1) {
                menu.setParentId(0);
                menu.setName(keys[0]);
                menu.setPath(keys[0]);
            } else {
                String path = "";
                for (int i = 0; i < keys.length - 1; i++) {
                    path += keys[i] + "/";
                }
                menu.setName(keys[keys.length - 1]);
                menu.setPath(String.join("/", keys));
                path = path.substring(0, path.length() - 1);
                menu.setParentId(map.get(path));
            }
            menus.add(menu);
        }
        System.out.println(menus);
      *//*  //获取父节点
        List<Menu> collect = menus.stream().filter(m -> m.getParentId() == 0).map(
                (m) -> {
                    m.setChildList(getChildrens(m, menus));
                    return m;
                }
        ).collect(Collectors.toList());*//*

        return menus;
    }

    private static List<Menu> getChildrens(Menu root, List<Menu> all) {
        List<Menu> children = all.stream().filter(m -> {
            return Objects.equals(m.getParentId(), root.getId());
        }).map((m) -> {
                    m.setChildList(getChildrens(m, all));
                    return m;
                }
        ).collect(Collectors.toList());
        return children;
    }*/

}
