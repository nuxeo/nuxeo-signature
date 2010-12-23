/*
 * (C) Copyright 2010 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *    Wojciech Sulejman
 */
package org.nuxeo.ecm.platform.signature.api.user;

import java.security.KeyStore;

import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.platform.signature.api.exception.CertException;

/**
 * High-level user certificate and keystore operations.
 *
 * These services help retrieving certificates, keystores and other information
 * related to specific users.
 *
 * @author <a href="mailto:ws@nuxeo.com">Wojciech Sulejman</a>
 *
 */
public interface CertUserService {

    /**
     * Generates user certificate and user keys, saves them to a user store, and
     * persists the store in the directory.
     *
     * @param user
     * @param userKeyPassword
     * @throws CertException
     * @throws ClientException
     */
    public DocumentModel createCert(DocumentModel user, String userKeyPassword)
            throws CertException, ClientException;

    /**
     * Retrieves a UserInfo object containing information needed for certificate
     * generation.
     *
     * @param userModel
     * @return UserInfo
     * @throws CertException
     */
    public UserInfo getUserInfo(DocumentModel userModel) throws CertException;

    /**
     * Returns simplified textual representation of a certificate representing its contents.
     *
     * @param certificate
     * @return Simple certificate string.
     */
    public String getUserCertInfo(DocumentModel user, String userKeyPassword)
            throws CertException, ClientException;

    /**
     * Retrieves user keystore from the directory.
     *
     * @param user
     * @param userKeyPassword
     * @return User KeyStore object
     * @throws CertException
     * @throws ClientException
     */
    public KeyStore getUserKeystore(String userID, String userKeyPassword)
            throws CertException, ClientException;

    /**
     * Retrieves a user certificate from the directory.
     *
     * @param user
     * @return certificate document model
     * @throws ClientException
     */
    public DocumentModel getCertificate(String userID) throws ClientException;

    /**
     * Checks if the user is present in the certificate directory.
     *
     * @param user
     * @return Is the user present in the certificate directory ?
     * @throws CertException
     */
    public boolean hasCertificateEntry(String userID) throws CertException,
            ClientException;

    /**
     * Deletes user entry from the certificate directory.
     * <p>
     * This is a high-level operation. The following containers/entries are
     * removed:
     * <ul>
     * <li>a certificate directory entry related to the userID
     * <li>a keystore (which was saved as a field in the directory entry)
     * <li>a private key and a public certificate (which were contained in the
     * keystore)
     * </ul>
     *
     * @param user
     * @throws CertException
     */
    public void deleteCertificateEntry(String userID) throws CertException,
            ClientException;

}
