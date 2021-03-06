package com.baidu.ueditor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.ueditor.define.ActionMap;

/**
 * 配置管理器 
 * 
 */
public final class MyConfigManager {

    private JSONObject jsonConfig = null;
    // 涂鸦上传filename定义
    private final static String SCRAWL_FILE_NAME = "scrawl";
    // 远程图片抓取filename定义
    private final static String REMOTE_FILE_NAME = "remote";
    private InputStream inputStream;

    /*
     * 通过一个给定的路径构建一个配置管理器， 该管理器要求地址路径所在目录下必须存在config.properties文件
     */
    private MyConfigManager(InputStream is) throws FileNotFoundException, IOException {

        this.inputStream = is;
        this.initEnv();

    }

    /**
     * 配置管理器构造工厂
     * 
     * @param rootPath
     *            服务器根路径
     * @param contextPath
     *            服务器所在项目路径
     * @param uri
     *            当前访问的uri
     * @return 配置管理器实例或者null
     */
    public static MyConfigManager getInstance(InputStream is) {

        try {
            return new MyConfigManager(is);
        } catch (Exception e) {
            return null;
        }

    }

    // 验证配置文件加载是否正确
    public boolean valid() {
        return this.jsonConfig != null;
    }

    public JSONObject getAllConfig() {

        return this.jsonConfig;

    }

    public Map<String, Object> getConfig(int type) {

        Map<String, Object> conf = new HashMap<String, Object>();
        String savePath = null;

        switch (type) {

        case ActionMap.UPLOAD_FILE:
            conf.put("isBase64", "false");
            conf.put("maxSize", this.jsonConfig.getLong("fileMaxSize"));
            conf.put("allowFiles", this.getArray("fileAllowFiles"));
            conf.put("fieldName", this.jsonConfig.getString("fileFieldName"));
            conf.put("iamgeRootPath", this.jsonConfig.getString("iamgeRootPath"));
            savePath = this.jsonConfig.getString("filePathFormat");
            break;

        case ActionMap.UPLOAD_IMAGE:
            conf.put("isBase64", "false");
            conf.put("maxSize", this.jsonConfig.getLong("imageMaxSize"));
            conf.put("allowFiles", this.getArray("imageAllowFiles"));
            conf.put("fieldName", this.jsonConfig.getString("imageFieldName"));
            conf.put("iamgeRootPath", this.jsonConfig.getString("iamgeRootPath"));
            savePath = this.jsonConfig.getString("imagePathFormat");
            break;

        case ActionMap.UPLOAD_VIDEO:
            conf.put("maxSize", this.jsonConfig.getLong("videoMaxSize"));
            conf.put("allowFiles", this.getArray("videoAllowFiles"));
            conf.put("fieldName", this.jsonConfig.getString("videoFieldName"));
            savePath = this.jsonConfig.getString("videoPathFormat");
            break;

        case ActionMap.UPLOAD_SCRAWL:
            conf.put("filename", MyConfigManager.SCRAWL_FILE_NAME);
            conf.put("maxSize", this.jsonConfig.getLong("scrawlMaxSize"));
            conf.put("fieldName", this.jsonConfig.getString("scrawlFieldName"));
            conf.put("isBase64", "true");
            savePath = this.jsonConfig.getString("scrawlPathFormat");
            break;

        case ActionMap.CATCH_IMAGE:
            conf.put("filename", MyConfigManager.REMOTE_FILE_NAME);
            conf.put("filter", this.getArray("catcherLocalDomain"));
            conf.put("maxSize", this.jsonConfig.getLong("catcherMaxSize"));
            conf.put("allowFiles", this.getArray("catcherAllowFiles"));
            conf.put("fieldName", this.jsonConfig.getString("catcherFieldName") + "[]");
            conf.put("iamgeRootPath", this.jsonConfig.getString("catcherRootPath"));
            savePath = this.jsonConfig.getString("catcherPathFormat");
            break;

        case ActionMap.LIST_IMAGE:
            conf.put("allowFiles", this.getArray("imageManagerAllowFiles"));
            conf.put("dir", this.jsonConfig.getString("imageManagerListPath"));
            conf.put("count", this.jsonConfig.getInteger("imageManagerListSize"));
            break;

        case ActionMap.LIST_FILE:
            conf.put("allowFiles", this.getArray("fileManagerAllowFiles"));
            conf.put("dir", this.jsonConfig.getString("fileManagerListPath"));
            conf.put("count", this.jsonConfig.getInteger("fileManagerListSize"));
            break;

        }

        conf.put("savePath", savePath);
//      conf.put("rootPath", this.rootPath);

        return conf;

    }

    @SuppressWarnings("static-access")
	private void initEnv() throws FileNotFoundException, IOException {

        String configContent = this.readFile();

        try {
            JSONObject jsonConfig = new JSONObject();
            this.jsonConfig = jsonConfig.parseObject(configContent);
        } catch (Exception e) {
            this.jsonConfig = null;
        }

    }

    private String[] getArray(String key) {

    	JSONArray jsonArray = this.jsonConfig.getJSONArray( key );
        String[] result = new String[jsonArray.size()];

        for (int i = 0, len = jsonArray.size(); i < len; i++) {
            result[i] = jsonArray.getString(i);
        }

        return result;

    }

    private String readFile() throws IOException {

        StringBuilder builder = new StringBuilder();

        try {

            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bfReader = new BufferedReader(reader);

            String tmpContent = null;

            while ((tmpContent = bfReader.readLine()) != null) {
                builder.append(tmpContent);
            }

            bfReader.close();

        } catch (UnsupportedEncodingException e) {
            // 忽略
        }

        return this.filter(builder.toString());

    }

    // 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
    private String filter(String input) {

        return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");

    }

}
