package cn.wf.wsw.tool;

import java.io.Serializable;

public class SaveBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7187263876487078930L;
	private String []baseAddress;
	private int baseIndex;
	private String []destAddress;
	private int destIndex;
	private String []picLocation;
	private int picIndex;

	private String []confLocation;
	private int confIndex;

	private String []manifestLocation;
	private int manifestIndex;

	private String []stringLocation;
	private int stringIndex;

	public int getBaseIndex() {
		return baseIndex;
	}
	public void setBaseIndex(int baseIndex) {
		this.baseIndex = baseIndex;
	}
	public int getDestIndex() {
		return destIndex;
	}
	public void setDestIndex(int destIndex) {
		this.destIndex = destIndex;
	}
	public int getPicIndex() {
		return picIndex;
	}
	public void setPicIndex(int picIndex) {
		this.picIndex = picIndex;
	}
	public int getConfIndex() {
		return confIndex;
	}
	public void setConfIndex(int confIndex) {
		this.confIndex = confIndex;
	}
	public int getManifestIndex() {
		return manifestIndex;
	}
	public void setManifestIndex(int manifestIndex) {
		this.manifestIndex = manifestIndex;
	}
	public int getStringIndex() {
		return stringIndex;
	}
	public void setStringIndex(int stringIndex) {
		this.stringIndex = stringIndex;
	}
	private String packageName;
	private String versionCode;
	private String versionName;
	private String appName;
	public String[] getDestAddress() {
		return destAddress;
	}
	public void setDestAddress(String[] destAddress) {
		this.destAddress = destAddress;
	}
	public String[] getBaseAddress() {
		return baseAddress;
	}
	public void setBaseAddress(String[] baseAddress) {
		this.baseAddress = baseAddress;
	}
	public String[] getPicLocation() {
		return picLocation;
	}
	public void setPicLocation(String[] picLocation) {
		this.picLocation = picLocation;
	}
	public String[] getConfLocation() {
		return confLocation;
	}
	public void setConfLocation(String[] confLocation) {
		this.confLocation = confLocation;
	}
	public String[] getManifestLocation() {
		return manifestLocation;
	}
	public void setManifestLocation(String[] manifestLocation) {
		this.manifestLocation = manifestLocation;
	}
	public String[] getStringLocation() {
		return stringLocation;
	}
	public void setStringLocation(String[] stringLocation) {
		this.stringLocation = stringLocation;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public SaveBean(String[] baseAddress, int baseIndex, String[] destAddress, int destIndex, String[] picLocation,
			int picIndex, String[] confLocation, int confIndex, String[] manifestLocation, int manifestIndex,
			String[] stringLocation, int stringIndex, String packageName, String versionCode, String versionName,
			String appName) {
		super();
		this.baseAddress = baseAddress;
		this.baseIndex = baseIndex;
		this.destAddress = destAddress;
		this.destIndex = destIndex;
		this.picLocation = picLocation;
		this.picIndex = picIndex;
		this.confLocation = confLocation;
		this.confIndex = confIndex;
		this.manifestLocation = manifestLocation;
		this.manifestIndex = manifestIndex;
		this.stringLocation = stringLocation;
		this.stringIndex = stringIndex;
		this.packageName = packageName;
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.appName = appName;
	}


	

}
