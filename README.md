# Permission
适配android 6.0 动态权限申请

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  	dependencies {
	        implementation 'com.github.zhangke666:Permission:v1.0'
	}
  
  
      dependencies {
        classpath 'com.android.tools.build:gradle:3.1.3'
        // aspectjx 插件   https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx
        classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.0'
        

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
    application 添加
    apply plugin: 'com.hujiang.android-aspectjx'
        
   如何使用？
   1. AndroidManifest.xml 添加动态权限
       <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    
    2. 可在  activity . fragment .service 中使用
     @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_two_permission:
                requestTwoPermission();
                break;
        }

    }
    @Permission(value = {Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE})
    private void requestTwoPermission() {
        Toast.makeText(this, "请求两个权限成功（写和相机）", Toast.LENGTH_SHORT).show();
    }
    @PermissionCanceled()
    private void cancel() {
        Log.i(TAG, "writeCancel: " );
        Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied()
    private void deny() {
        Log.i(TAG, "writeDeny:");
        Toast.makeText(this, "deny", Toast.LENGTH_SHORT).show();
    }
   
    
    
    
    


