package cn.wf.wsw.tool;

import java.io.Serializable;

public class SaveBean implements Serializable{
	private String []baseAddress;
	private String []pictureAddress;
	private String []configFileAddress;
	private boolean isDirectOverride=false;
	private String []manifestAddress;
	private String []stringAddress;
	public String[] getManifestAddress() {
		return manifestAddress;
	}
	public void setManifestAddress(String[] manifestAddress) {
		this.manifestAddress = manifestAddress;
	}

	public String[] getStringAddress() {
		return stringAddress;
	}
	public void setStringAddress(String[] stringAddress) {
		this.stringAddress = stringAddress;
	}

	public SaveBean(String[] baseAddress, String[] pictureAddress,
			String[] configFileAddress, boolean isDirectOverride,
			String[] manifestAddress, String[] stringAddress) {
		super();
		this.baseAddress = baseAddress;
		this.pictureAddress = pictureAddress;
		this.configFileAddress = configFileAddress;
		this.isDirectOverride = isDirectOverride;
		this.manifestAddress = manifestAddress;
		this.stringAddress = stringAddress;
	}
	public boolean isDirectOverride() {
		return isDirectOverride;
	}
	public void setDirectOverride(boolean isDirectOverride) {
		this.isDirectOverride = isDirectOverride;
	}
	public String[] getBaseAddress() {
		return baseAddress;
	}
	public void setBaseAddress(String[] baseAddress) {
		this.baseAddress = baseAddress;
	}
	public String[] getPictureAddress() {
		return pictureAddress;
	}
	public void setPictureAddress(String[] pictureAddress) {
		this.pictureAddress = pictureAddress;
	}
	public String[] getConfigFileAddress() {
		return configFileAddress;
	}
	public void setConfigFileAddress(String[] configFileAddress) {
		this.configFileAddress = configFileAddress;
	}


}
