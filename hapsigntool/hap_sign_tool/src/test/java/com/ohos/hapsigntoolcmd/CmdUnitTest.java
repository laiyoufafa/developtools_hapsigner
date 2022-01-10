/*
 * Copyright (c) 2021-2022 Huawei Device Co., Ltd.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ohos.hapsigntoolcmd;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.ohos.hapsigntool.key.KeyPairTools;
import com.ohos.hapsigntool.utils.FileUtils;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.ohos.hapsigntool.HapSignTool;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * CmdUnitTest.
 *
 * @since 2021/12/28
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CmdUnitTest {

    /**
     * Command line parameter appCaCertFile.
     */
    public static final String CMD_SUB_CA_CERT_FILE = "-subCaCertFile";
    /**
     * Command line parameter outFile.
     */
    public static final String CMD_OUT_FILE = "-outFile";
    /**
     * Command line parameter basicConstraints.
     */
    public static final String CMD_BASIC_CONSTRAINTS = "-basicConstraints";
    /**
     * Command line parameter basicConstraintsCa.
     */
    public static final String CMD_BASIC_CONSTRAINTS_CA = "-basicConstraintsCa";
    /**
     * Command line parameter basicConstraintsCritical.
     */
    public static final String CMD_BASIC_CONSTRAINTS_CRITICAL = "-basicConstraintsCritical";
    /**
     * Command line parameter basicConstraintsPathLen.
     */
    public static final String CMD_BASIC_CONSTRAINTS_PATH_LEN = "-basicConstraintsPathLen";
    /**
     * Command line parameter caCertFile.
     */
    public static final String CMD_ROOT_CA_CERT_FILE = "-rootCaCertFile";
    /**
     * Command line parameter outForm.
     */
    public static final String CMD_OUT_FORM = "-outForm";
    /**
     * Command line parameter cert.
     */
    public static final String CMD_CERT_CHAIN = "certChain";
    /**
     * Command line parameter digitalSignature.
     */
    public static final String CMD_DIGITAL_SIGNATURE = "digitalSignature";
    /**
     * Command line parameter codeSignature and emailProtection.
     */
    public static final String CMD_CODE_AND_EMAIL = "codeSignature,emailProtection";
    /**
     * Command line parameter mode.
     */
    public static final String CMD_MODE = "-mode";
    /**
     * Command line parameter keystoreFile.
     */
    public static final String CMD_KEY_STORE_FILE = "-keystoreFile";
    /**
     * Command line parameter keystorePwd.
     */
    public static final String CMD_KEY_STORE_RIGHTS = "-keystorePwd";
    /**
     * Command line parameter keyAlg.
     */
    public static final String CMD_KEY_ALG = "-keyAlg";
    /**
     * Command line parameter keyAlias.
     */
    public static final String CMD_KEY_ALIAS = "-keyAlias";
    /**
     * Command line parameter keyPwd.
     */
    public static final String CMD_KEY_RIGHTS = "-keyPwd";
    /**
     * Command line parameter keySize.
     */
    public static final String CMD_KEY_SIZE = "-keySize";
    /**
     * Command line parameter keyUsage.
     */
    public static final String CMD_KEY_USAGE = "-keyUsage";
    /**
     * Command line parameter keyUsageCritical.
     */
    public static final String CMD_KEY_USAGE_CRITICAL = "-keyUsageCritical";
    /**
     * Command line parameter extKeyUsage.
     */
    public static final String CMD_EXT_KEY_USAGE = "-extKeyUsage";
    /**
     * Command line parameter extKeyUsageCritical.
     */
    public static final String CMD_EXT_KEY_USAGE_CRITICAL = "-extKeyUsageCritical";
    /**
     * Command line parameter profileCertFile.
     */
    public static final String CMD_PROFILE_CERT_FILE = "-profileCertFile";
    /**
     * Command line parameter profileCaCertFile.
     */
    public static final String CMD_PROFILE_CA_CERT_FILE = "-profileCaCertFile";
    /**
     * Command line parameter subject.
     */
    public static final String CMD_SUBJECT = "-subject";
    /**
     * Command line parameter signAlg.
     */
    public static final String CMD_SIGN_ALG = "-signAlg";
    /**
     * Command line parameter inFile.
     */
    public static final String CMD_IN_FILE = "-inFile";
    /**
     * Command line parameter issuer.
     */
    public static final String CMD_ISSUER = "-issuer";
    /**
     * Command line parameter issuerKeyAlias.
     */
    public static final String CMD_ISSUER_KEY_ALIAS = "-issuerKeyAlias";
    /**
     * Command line parameter issuerKeyPwd.
     */
    public static final String CMD_ISSUER_KEY_RIGHTS = "-issuerKeyPwd";
    /**
     * Command line parameter validity.
     */
    public static final String CMD_VALIDITY = "-validity";
    /**
     * Command line parameter false.
     */
    public static final String CMD_FALSE = "false";
    /**
     * Command line parameter true.
     */
    public static final String CMD_TRUE = "true";
    /**
     * Command line parameter basicConstraintsPathLen is 0.
     */
    public static final String CMD_BC_PATH_LEN_0 = "0";
    /**
     * Command line parameter password is 123456.
     */
    public static final String CMD_RIGHTS_123456 = "123456";
    /**
     * Command line parameter RSA is 2048.
     */
    public static final String CMD_RSA_2048 = "2048";
    /**
     * Command line parameter validity is 365.
     */
    public static final String CMD_VALIDITY_365 = "365";
    /**
     * Command line parameter json file is UnsgnedDebugProfileTemplate.
     */
    public static final String CMD_JSON_FILE = "UnsgnedDebugProfileTemplate.json";
    /**
     * Command line parameter localSign.
     */
    public static final String CMD_LOCAL_SIGN = "localSign";
    /**
     * Command line parameter SHA256withRSA.
     */
    public static final String CMD_SHA_256_WITH_RSA = "SHA256withRSA";
    /**
     * Command line parameter cer file is test_app-cert.
     */
    public static final String CMD_APP_CERT_PATH = "test_app-cert.cer";
    /**
     * Command line parameter cer file is test_cert.
     */
    public static final String CMD_CERT_PATH = "test_cert.cer";
    /**
     * Command line parameter csr file is test_csr.
     */
    public static final String CMD_CSR_PATH = "test_csr.csr";
    /**
     * Command line parameter jks file is test_csr.
     */
    public static final String CMD_KEY_STORE_PATH = "test_keypair.jks";
    /**
     * Command line parameter cer file is test_root_ca.
     */
    public static final String CMD_ROOT_CA_PATH = "test_root_ca.cer";
    /**
     * Command line parameter cer file is test_sub_ca.
     */
    public static final String CMD_SUB_CA_PATH = "test_sub_ca.cer";
    /**
     * Command line parameter p7b file is test_sign_profile.
     */
    public static final String CMD_SIGN_PROFILE_PATH = "test_sign_profile.p7b";
    /**
     * Command line parameter cer file is test_profile-cert.
     */
    public static final String CMD_PROFILE_CERT_PATH = "test_profile-cert.cer";
    /**
     * Command line parameter cer file is test_verify_profile.
     */
    public static final String CMD_VERIFY_PROFILE_PATH = "test_verify_profile.cer";
    /**
     * Command line parameter oh-app-ca-v1.
     */
    public static final String CMD_OH_APP_CA_V1 = "oh-app-ca-v1";
    /**
     * Command line parameter oh-app1-key-v1.
     */
    public static final String CMD_OH_APP1_KEY_V1 = "oh-app1-key-v1";
    /**
     * Command line parameter CN=App1 Release.
     */
    public static final String CMD_APP1_RELEASE = "C=CN,O=OpenHarmony,OU=OpenHarmony Community,CN=App1 Release";
    /**
     * Command line parameter CN=Provision Profile Release.
     */
    public static final String CMD_PP_RELEASE = "C=CN,O=OpenHarmony,"
            + "OU=OpenHarmony Community,CN=Provision Profile Release";
    /**
     * Command line parameter CN=Provision Profile Signature Service CA.
     */
    public static final String CMD_PP_CA = "C=CN,O=OpenHarmony,OU=OpenHarmony Community,"
            + "CN=Provision Profile Signature Service CA";
    /**
     * Command line parameter CN=Application Signature Service CA.
     */
    public static final String CMD_APP_CA = "C=CN,"
            + "O=OpenHarmony,OU=OpenHarmony Community,CN=Application Signature Service CA";

    /**
     * Add log info.
     */
    private final Logger logger = LoggerFactory.getLogger(CmdUnitTest.class);
    @Order(1)
    @Test
    public void testCmdKeypair() throws IOException {

        try {
            deleteFile(CMD_KEY_STORE_PATH);
            boolean result = HapSignTool.processCmd(new String[]{CmdUtil.Method.GENERATE_KEYPAIR});
            assertFalse(result);
            assertFalse(FileUtils.isFileExist(CMD_KEY_STORE_PATH));
        } catch (Exception exception) {
            logger.info(exception, () -> exception.getMessage());
        }

        deleteFile(CMD_KEY_STORE_PATH);
        boolean result = HapSignTool.processCmd(new String[]{
            CmdUtil.Method.GENERATE_KEYPAIR,
            CMD_KEY_ALIAS, CMD_OH_APP1_KEY_V1,
            CMD_KEY_RIGHTS, CMD_RIGHTS_123456,
            CMD_KEY_ALG, KeyPairTools.RSA,
            CMD_KEY_SIZE, CMD_RSA_2048,
            CMD_KEY_STORE_FILE, CMD_KEY_STORE_PATH,
            CMD_KEY_STORE_RIGHTS, CMD_RIGHTS_123456});
        assertTrue(result);
        assertTrue(FileUtils.isFileExist(CMD_KEY_STORE_PATH));
        result = HapSignTool.processCmd(new String[]{
            CmdUtil.Method.GENERATE_KEYPAIR,
            CMD_KEY_ALIAS, CMD_OH_APP_CA_V1,
            CMD_KEY_RIGHTS, CMD_RIGHTS_123456,
            CMD_KEY_ALG, KeyPairTools.RSA,
            CMD_KEY_SIZE, CMD_RSA_2048,
            CMD_KEY_STORE_FILE, CMD_KEY_STORE_PATH,
            CMD_KEY_STORE_RIGHTS, CMD_RIGHTS_123456});
        assertTrue(result);
        assertTrue(FileUtils.isFileExist(CMD_KEY_STORE_PATH));
    }

    /**
     * Csr test case.
     *
     * @throws IOException Error
     */
    @Order(2)
    @Test
    public void testCmdCsr() throws IOException {

        try {
            deleteFile(CMD_CSR_PATH);
            boolean result = HapSignTool.processCmd(new String[]{CmdUtil.Method.GENERATE_CSR});
            assertFalse(result);
            assertFalse(FileUtils.isFileExist(CMD_CSR_PATH));
        } catch (Exception exception) {
            logger.info(exception, () -> exception.getMessage());
        }

        deleteFile(CMD_CSR_PATH);
        boolean result = HapSignTool.processCmd(new String[]{
            CmdUtil.Method.GENERATE_CSR,
            CMD_KEY_ALIAS, CMD_OH_APP1_KEY_V1,
            CMD_KEY_RIGHTS, CMD_RIGHTS_123456,
            CMD_SUBJECT, CMD_APP1_RELEASE,
            CMD_SIGN_ALG, CMD_SHA_256_WITH_RSA,
            CMD_KEY_STORE_FILE, CMD_KEY_STORE_PATH,
            CMD_KEY_STORE_RIGHTS, CMD_RIGHTS_123456,
            CMD_OUT_FILE, CMD_CSR_PATH});
        assertTrue(result);
        assertTrue(FileUtils.isFileExist(CMD_CSR_PATH));
    }

    /**
     * Cert test case
     *
     * @throws IOException Error
     */
    @Order(3)
    @Test
    public void testCmdCert() throws IOException {

        try {
            deleteFile(CMD_CERT_PATH);
            boolean result = HapSignTool.processCmd(new String[]{CmdUtil.Method.GENERATE_CERT});
            assertFalse(result);
            assertFalse(FileUtils.isFileExist(CMD_CERT_PATH));
        } catch (Exception exception) {
            logger.info(exception, () -> exception.getMessage());
        }

        deleteFile(CMD_CERT_PATH);
        boolean result = HapSignTool.processCmd(new String[]{
            CmdUtil.Method.GENERATE_CERT,
            CMD_KEY_ALIAS, CMD_OH_APP1_KEY_V1,
            CMD_KEY_RIGHTS, CMD_RIGHTS_123456,
            CMD_ISSUER, CMD_APP_CA,
            CMD_SIGN_ALG, CMD_SHA_256_WITH_RSA,
            CMD_KEY_STORE_FILE, CMD_KEY_STORE_PATH,
            CMD_KEY_STORE_RIGHTS, CMD_RIGHTS_123456,
            CMD_OUT_FILE, CMD_CERT_PATH,
            CMD_ISSUER_KEY_ALIAS, CMD_OH_APP1_KEY_V1,
            CMD_ISSUER_KEY_RIGHTS, CMD_RIGHTS_123456,
            CMD_SUBJECT, CMD_APP1_RELEASE,
            CMD_VALIDITY, CMD_VALIDITY_365,
            CMD_KEY_USAGE, CMD_DIGITAL_SIGNATURE,
            CMD_KEY_USAGE_CRITICAL, CMD_FALSE,
            CMD_EXT_KEY_USAGE, CMD_CODE_AND_EMAIL,
            CMD_EXT_KEY_USAGE_CRITICAL, CMD_TRUE,
            CMD_BASIC_CONSTRAINTS, CMD_FALSE,
            CMD_BASIC_CONSTRAINTS_CRITICAL, CMD_TRUE,
            CMD_BASIC_CONSTRAINTS_CA, CMD_FALSE,
            CMD_BASIC_CONSTRAINTS_PATH_LEN, CMD_BC_PATH_LEN_0});
        assertTrue(result);
        assertTrue(FileUtils.isFileExist(CMD_CERT_PATH));
    }

    /**
     * Ca test case.
     *
     * @throws IOException Error
     */
    @Order(4)
    @Test
    public void testCmdCa() throws IOException {
        try {
            deleteFile(CMD_ROOT_CA_PATH);
            deleteFile(CMD_SUB_CA_PATH);
            boolean result = HapSignTool.processCmd(new String[]{CmdUtil.Method.GENERATE_CA});
            assertFalse(result);
            assertFalse(FileUtils.isFileExist(CMD_ROOT_CA_PATH));
            assertFalse(FileUtils.isFileExist(CMD_SUB_CA_PATH));
        } catch (Exception exception) {
            logger.info(exception, () -> exception.getMessage());
        }
        deleteFile(CMD_ROOT_CA_PATH);
        boolean result1 = HapSignTool.processCmd(new String[]{
            CmdUtil.Method.GENERATE_CA,
            CMD_KEY_ALIAS, CMD_OH_APP_CA_V1,
            CMD_KEY_RIGHTS, CMD_RIGHTS_123456,
            CMD_KEY_ALG, KeyPairTools.RSA,
            CMD_KEY_SIZE, CMD_RSA_2048,
            CMD_KEY_STORE_FILE, CMD_KEY_STORE_PATH,
            CMD_KEY_STORE_RIGHTS, CMD_RIGHTS_123456,
            CMD_OUT_FILE, CMD_ROOT_CA_PATH,
            CMD_SUBJECT, CMD_APP_CA,
            CMD_VALIDITY, CMD_VALIDITY_365,
            CMD_SIGN_ALG, CMD_SHA_256_WITH_RSA,
            CMD_BASIC_CONSTRAINTS_PATH_LEN, CMD_BC_PATH_LEN_0});
        assertTrue(result1);
        assertTrue(FileUtils.isFileExist(CMD_ROOT_CA_PATH));
        deleteFile(CMD_SUB_CA_PATH);
        boolean result2 = HapSignTool.processCmd(new String[]{
            CmdUtil.Method.GENERATE_CA,
            CMD_KEY_ALIAS, CMD_OH_APP1_KEY_V1,
            CMD_KEY_RIGHTS, CMD_RIGHTS_123456,
            CMD_ISSUER, CMD_APP_CA,
            CMD_KEY_ALG, KeyPairTools.RSA,
            CMD_KEY_SIZE, CMD_RSA_2048,
            CMD_KEY_STORE_FILE, CMD_KEY_STORE_PATH,
            CMD_KEY_STORE_RIGHTS, CMD_RIGHTS_123456,
            CMD_OUT_FILE, CMD_SUB_CA_PATH,
            CMD_ISSUER_KEY_ALIAS, CMD_OH_APP_CA_V1,
            CMD_ISSUER_KEY_RIGHTS, CMD_RIGHTS_123456,
            CMD_SUBJECT, CMD_APP_CA,
            CMD_VALIDITY, CMD_VALIDITY_365,
            CMD_SIGN_ALG, CMD_SHA_256_WITH_RSA,
            CMD_BASIC_CONSTRAINTS_PATH_LEN, CMD_BC_PATH_LEN_0});
        assertTrue(result2);
        assertTrue(FileUtils.isFileExist(CMD_SUB_CA_PATH));
    }

    /**
     * App cert test case.
     *
     * @throws IOException Error
     */
    @Order(5)
    @Test
    public void testCmdAppCert() throws IOException {

        try {
            deleteFile(CMD_APP_CERT_PATH);
            boolean result = HapSignTool.processCmd(new String[]{CmdUtil.Method.GENERATE_APP_CERT});
            assertFalse(result);
            assertFalse(FileUtils.isFileExist(CMD_APP_CERT_PATH));
        } catch (Exception exception) {
            logger.info(exception, () -> exception.getMessage());
        }
        deleteFile(CMD_APP_CERT_PATH);
        boolean result = HapSignTool.processCmd(new String[]{
            CmdUtil.Method.GENERATE_APP_CERT,
            CMD_KEY_ALIAS, CMD_OH_APP1_KEY_V1,
            CMD_KEY_RIGHTS, CMD_RIGHTS_123456,
            CMD_ISSUER, CMD_APP_CA,
            CMD_KEY_STORE_FILE, CMD_KEY_STORE_PATH,
            CMD_KEY_STORE_RIGHTS, CMD_RIGHTS_123456,
            CMD_OUT_FILE, CMD_APP_CERT_PATH,
            CMD_ISSUER_KEY_ALIAS, CMD_OH_APP_CA_V1,
            CMD_ISSUER_KEY_RIGHTS, CMD_RIGHTS_123456,
            CMD_SUBJECT, CMD_APP1_RELEASE,
            CMD_VALIDITY, CMD_VALIDITY_365,
            CMD_OUT_FORM, CMD_CERT_CHAIN,
            CMD_ROOT_CA_CERT_FILE, CMD_ROOT_CA_PATH,
            CMD_SUB_CA_CERT_FILE, CMD_SUB_CA_PATH,
            CMD_SIGN_ALG, CMD_SHA_256_WITH_RSA});
        assertTrue(result);
        assertTrue(FileUtils.isFileExist(CMD_APP_CERT_PATH));
    }

    /**
     * Profile cert test case
     *
     * @throws IOException Error
     */
    @Order(6)
    @Test
    public void testCmdProfileCert() throws IOException {
        try {
            deleteFile(CMD_PROFILE_CERT_PATH);
            boolean result = HapSignTool.processCmd(new String[]{CmdUtil.Method.GENERATE_PROFILE_CERT});
            assertFalse(result);
            assertFalse(FileUtils.isFileExist(CMD_PROFILE_CERT_PATH));
        } catch (Exception exception) {
            logger.info(exception, () -> exception.getMessage());
        }

        deleteFile(CMD_PROFILE_CERT_PATH);
        boolean result = HapSignTool.processCmd(new String[]{
            CmdUtil.Method.GENERATE_PROFILE_CERT,
            CMD_KEY_ALIAS, CMD_OH_APP1_KEY_V1,
            CMD_KEY_RIGHTS, CMD_RIGHTS_123456,
            CMD_ISSUER, CMD_PP_CA,
            CMD_KEY_STORE_FILE, CMD_KEY_STORE_PATH,
            CMD_KEY_STORE_RIGHTS, CMD_RIGHTS_123456,
            CMD_OUT_FILE, CMD_PROFILE_CERT_PATH,
            CMD_ISSUER_KEY_ALIAS, CMD_OH_APP_CA_V1,
            CMD_ISSUER_KEY_RIGHTS, CMD_RIGHTS_123456,
            CMD_SUBJECT, CMD_PP_RELEASE,
            CMD_VALIDITY, CMD_VALIDITY_365,
            CMD_OUT_FORM, CMD_CERT_CHAIN,
            CMD_ROOT_CA_CERT_FILE, CMD_ROOT_CA_PATH,
            CMD_SUB_CA_CERT_FILE, CMD_SUB_CA_PATH,
            CMD_SIGN_ALG, CMD_SHA_256_WITH_RSA});
        assertTrue(result);
        assertTrue(FileUtils.isFileExist(CMD_PROFILE_CERT_PATH));
    }

    /**
     * Sign profile test case.
     *
     * @throws IOException error
     */
    @Order(7)
    @Test
    public void testCmdSignProfile() throws IOException {

        try {
            deleteFile(CMD_SIGN_PROFILE_PATH);
            boolean result = HapSignTool.processCmd(new String[]{CmdUtil.Method.SIGN_PROFILE});
            assertFalse(result);
            assertFalse(FileUtils.isFileExist(CMD_SIGN_PROFILE_PATH));
        } catch (Exception exception) {
            logger.info(exception, () -> exception.getMessage());
        }

        deleteFile(CMD_SIGN_PROFILE_PATH);
        loadFile(CMD_JSON_FILE);
        boolean result = HapSignTool.processCmd(new String[]{
            CmdUtil.Method.SIGN_PROFILE,
            CMD_MODE, CMD_LOCAL_SIGN,
            CMD_KEY_ALIAS, CMD_OH_APP1_KEY_V1,
            CMD_KEY_RIGHTS, CMD_RIGHTS_123456,
            CMD_PROFILE_CERT_FILE, CMD_PROFILE_CERT_PATH,
            CMD_IN_FILE, CMD_JSON_FILE,
            CMD_SIGN_ALG, CMD_SHA_256_WITH_RSA,
            CMD_KEY_STORE_FILE, CMD_KEY_STORE_PATH,
            CMD_KEY_STORE_RIGHTS, CMD_RIGHTS_123456,
            CMD_OUT_FILE, CMD_SIGN_PROFILE_PATH});
        assertTrue(result);
        assertTrue(FileUtils.isFileExist(CMD_SIGN_PROFILE_PATH));
    }

    /**
     * Verify profile test case.
     */
    @Order(8)
    @Test
    public void testVerifyProfile() {
        try {
            boolean result = HapSignTool.processCmd(new String[]{CmdUtil.Method.VERIFY_PROFILE});
            assertFalse(result);
        } catch (Exception exception) {
            logger.info(exception, () -> exception.getMessage());
        }

        boolean result = HapSignTool.processCmd(new String[]{
            CmdUtil.Method.VERIFY_PROFILE,
            CMD_IN_FILE, CMD_SIGN_PROFILE_PATH,
            CMD_OUT_FILE, CMD_VERIFY_PROFILE_PATH});
        assertTrue(result);
    }

    /**
     * Sign hap test case.
     */
    @Order(9)
    @Test
    public void testCmdSignApp() {
        try {
            boolean result = HapSignTool.processCmd(new String[]{CmdUtil.Method.SIGN_APP});
            assertFalse(result);
        } catch (Exception exception) {
            logger.info(exception, () -> exception.getMessage());
        }
    }

    /**
     * Verify signed app test case.
     */
    @Order(10)
    @Test
    public void testCmdVerifyApp() {
        try {
            boolean result = HapSignTool.processCmd(new String[]{CmdUtil.Method.VERIFY_APP});
            assertFalse(result);
        } catch (Exception exception) {
            logger.info(exception, () -> exception.getMessage());
        }
    }

    private void loadFile(String filePath) throws IOException {
        ClassLoader classLoader = CmdUnitTest.class.getClassLoader();
        InputStream fileInputStream = classLoader.getResourceAsStream(filePath);
        byte[] fileData = FileUtils.read(fileInputStream);
        FileUtils.write(fileData, new File(filePath));
    }

    private void deleteFile(String filePath) throws IOException {
        if (FileUtils.isFileExist(filePath)) {
            Path path = Paths.get(filePath);
            Files.delete(path);
        }
    }
}
