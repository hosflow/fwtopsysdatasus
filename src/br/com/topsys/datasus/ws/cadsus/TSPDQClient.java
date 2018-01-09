package br.com.topsys.datasus.ws.cadsus;

//Nome do pacote utilizado
import br.com.topsys.datasus.ws.cadsus.TSUsuarioCadSus;
import br.com.topsys.datasus.ws.cadsus.TSWSSUsernameTokenSecurityHandler;
import br.com.topsys.datasus.ws.cadsus.TSEnderecoCadSus;
import ihe.iti.pdqv3._2007.PDQSupplierPortType;
import ihe.iti.pdqv3._2007.PDQSupplierService;
import java.io.Serializable;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.JAXBElement;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import org.hl7.v3.AD;

import org.hl7.v3.ActClassControlAct;
import org.hl7.v3.AdxpAdditionalLocator;
import org.hl7.v3.AdxpCity;
import org.hl7.v3.AdxpCountry;
import org.hl7.v3.AdxpHouseNumber;
import org.hl7.v3.AdxpPostalCode;
import org.hl7.v3.AdxpState;
import org.hl7.v3.AdxpStreetAddressLine;
import org.hl7.v3.AdxpStreetName;
import org.hl7.v3.AdxpStreetNameType;
import org.hl7.v3.AdxpUnitID;
import org.hl7.v3.CD;
import org.hl7.v3.CE;
import org.hl7.v3.COCTMT030007UVPerson;
import org.hl7.v3.CS;
import org.hl7.v3.CommunicationFunctionType;
import org.hl7.v3.EN;
import org.hl7.v3.EnGiven;
import org.hl7.v3.EntityClassDevice;
import org.hl7.v3.II;
import org.hl7.v3.IVLTS;
import org.hl7.v3.MCCIMT000100UV01Device;
import org.hl7.v3.MCCIMT000100UV01Receiver;
import org.hl7.v3.MCCIMT000100UV01Sender;
import org.hl7.v3.PDQObjectFactory;
import org.hl7.v3.PN;
import org.hl7.v3.PRPAIN201301UV02MFMIMT700701UV01Subject1;
import org.hl7.v3.PRPAIN201305UV02;
import org.hl7.v3.PRPAIN201305UV02QUQIMT021001UV01ControlActProcess;
import org.hl7.v3.PRPAIN201306UV02;
import org.hl7.v3.PRPAIN201306UV02MFMIMT700711UV01Subject1;
import org.hl7.v3.PRPAMT201306UV02LivingSubjectAdministrativeGender;
import org.hl7.v3.PRPAMT201306UV02LivingSubjectBirthTime;
import org.hl7.v3.PRPAMT201306UV02LivingSubjectId;
import org.hl7.v3.PRPAMT201306UV02LivingSubjectName;
import org.hl7.v3.PRPAMT201306UV02MothersMaidenName;
import org.hl7.v3.PRPAMT201306UV02ParameterList;
import org.hl7.v3.PRPAMT201306UV02QueryByParameter;
import org.hl7.v3.PRPAMT201310UV02Citizen;
import org.hl7.v3.PRPAMT201310UV02OtherIDs;
import org.hl7.v3.PRPAMT201310UV02Person;
import org.hl7.v3.PRPAMT201310UV02PersonalRelationship;
import org.hl7.v3.ST;
import org.hl7.v3.TEL;
import org.hl7.v3.TS;
import org.hl7.v3.XActMoodIntentEvent;

public class TSPDQClient {

    private static final PDQSupplierService SERVICE;
    private PRPAMT201306UV02ParameterList parameterList;
    private PDQSupplierPortType pdq;
    private PRPAIN201305UV02 body;
    private PRPAMT201306UV02QueryByParameter queryByParamenter;
    private PRPAIN201305UV02QUQIMT021001UV01ControlActProcess controlAct;

    static {

        // CONFIGURA A JVM PARA IGNORAR O CERTIFICADOS INVALIDOS
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};

        // Install the all-trusting trust manager
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException e1) {
            throw new RuntimeException(e1);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }

        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        SERVICE = new PDQSupplierService();

        // Handlers para acionar os parametros de autenticacao no cabecalho da
        // mensagem
        final TSWSSUsernameTokenSecurityHandler handler = new TSWSSUsernameTokenSecurityHandler("CADSUS.CNS.PDQ.PUBLICO", "kUXNmiiii#RDdlOELdoe00966");
        SERVICE.setHandlerResolver(new HandlerResolver() {

            @Override
            @SuppressWarnings("rawtypes")
            public List<Handler> getHandlerChain(PortInfo arg0) {
                List<Handler> handlerList = new ArrayList<Handler>();
                handlerList.add(handler);
                return handlerList;
            }
        });
    }

    public TSPDQClient() {
        configuracaoInicial();
    }

    private void configuracaoInicial() {
        pdq = SERVICE.getPDQSupplierPortSoap12();

        body = new PRPAIN201305UV02();
        body.setITSVersion("XML_1.0");
        // Parte fixa
        // <id root="2.16.840.1.113883.4.714" extension="123456"/>
        II ii = new II();
        ii.setRoot("2.16.840.1.113883.4.714");
        ii.setExtension("123456");
        body.setId(ii);
        // <creationTime value="20070428150301"/>
        TS ts = new TS();
        //ts.setValue(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        ts.setValue(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        body.setCreationTime(ts);
        // <interactionId root="2.16.840.1.113883.1.6"
        // extension="PRPA_IN201305UV02"/>
        II intId = new II();
        intId.setRoot("2.16.840.1.113883.1.6");
        intId.setExtension("PRPA_IN201305UV02");
        body.setInteractionId(intId);
        // <processingCode code="T"/>
        CS processingCode = new CS();
        processingCode.setCode("T");
        body.setProcessingCode(processingCode);
        // <processingModeCode code="T"/>
        body.setProcessingModeCode(processingCode);
        // <acceptAckCode code="AL"/>
        CS acceptAckCode = new CS();
        acceptAckCode.setCode("AL");
        body.setAcceptAckCode(acceptAckCode);
        // <receiver typeCode="RCV">
        MCCIMT000100UV01Receiver receiver = new MCCIMT000100UV01Receiver();
        receiver.setTypeCode(CommunicationFunctionType.RCV);
        // <device classCode="DEV" determinerCode="INSTANCE">
        MCCIMT000100UV01Device deviceReceiver = new MCCIMT000100UV01Device();
        deviceReceiver.setClassCode(EntityClassDevice.DEV);
        deviceReceiver.setDeterminerCode("INSTANCE");
        // <id root="2.16.840.1.113883.3.72.6.5.100.85"/>
        II idDeviceReceiver = new II();
        idDeviceReceiver.setRoot("2.16.840.1.113883.3.72.6.5.100.85");
        deviceReceiver.getId().add(idDeviceReceiver);
        // </device>
        receiver.setDevice(deviceReceiver);
        body.getReceiver().add(receiver);
        // </receiver>
        // <sender typeCode="SND">
        MCCIMT000100UV01Sender sender = new MCCIMT000100UV01Sender();
        sender.setTypeCode(CommunicationFunctionType.SND);
        // <device classCode="DEV" determinerCode="INSTANCE">
        MCCIMT000100UV01Device deviceSender = new MCCIMT000100UV01Device();
        deviceSender.setClassCode(EntityClassDevice.DEV);
        deviceSender.setDeterminerCode("INSTANCE");
        // <id root="2.16.840.1.113883.3.72.6.2"/>
        II idDeviceSender = new II();
        idDeviceSender.setRoot("2.16.840.1.113883.3.72.6.2");
        deviceSender.getId().add(idDeviceSender);
        // <name>SIGA-SAUDE</name>
        EN nameSender = new EN();
        nameSender.getContent().add("SIGA");
        deviceSender.getName().add(nameSender);
        // </device>
        sender.setDevice(deviceSender);
        body.setSender(sender);
        // </sender>

        // <controlActProcess classCode="CACT" moodCode="EVN">
        controlAct = new PRPAIN201305UV02QUQIMT021001UV01ControlActProcess();
        controlAct.setClassCode(ActClassControlAct.CACT);
        controlAct.setMoodCode(XActMoodIntentEvent.EVN);
        body.setControlActProcess(controlAct);
        // <code code="PRPA_TE201305UV02" codeSystem="2.16.840.1.113883.1.6"/>
        CD code = new CD();
        code.setCode("PRPA_TE201305UV02");
        code.setCodeSystem("2.16.840.1.113883.1.6");
        controlAct.setCode(code);
        // <queryByParameter>
        queryByParamenter = new PRPAMT201306UV02QueryByParameter();
        // <queryId root="1.2.840.114350.1.13.28.1.18.5.999"
        // extension="1840997084" />
        II queryId = new II();
        queryId.setRoot("1.2.840.114350.1.13.28.1.18.5.999");
        queryId.setExtension("1840997084");
        queryByParamenter.setQueryId(queryId);
        // <statusCode code="new"/>
        CS statusCode = new CS();
        statusCode.setCode("new");
        queryByParamenter.setStatusCode(statusCode);
        // <responseModalityCode code="R"/>
        CS responseModalityCode = new CS();
        responseModalityCode.setCode("R");
        queryByParamenter.setResponseModalityCode(responseModalityCode);
        // <responsePriorityCode code="I"/>
        CS responsePriorityCode = new CS();
        responsePriorityCode.setCode("I");
        queryByParamenter.setResponsePriorityCode(responsePriorityCode);
        // <parameterList>
        parameterList = new PRPAMT201306UV02ParameterList();
        queryByParamenter.setParameterList(parameterList);

        obterUsuarioCadSus();

    }

    public List<TSUsuarioCadSus> pesquisarCadSus(TSUsuarioCadSus usuario) {

        if (usuario.getDataNascimento() != null) {
            //Data de Nascimento
            PRPAMT201306UV02LivingSubjectBirthTime livingSubjectBirhtTime = new PRPAMT201306UV02LivingSubjectBirthTime();
            parameterList.getLivingSubjectBirthTime().add(livingSubjectBirhtTime);
            IVLTS birthTime = new IVLTS();
            
             SimpleDateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy");
	    Date data = null;
            try {
                data = dtFormat.parse(usuario.getDataNascimento());
            } catch (ParseException ex) {
                Logger.getLogger(TSPDQClient.class.getName()).log(Level.SEVERE, null, ex);
            }
 
            birthTime.setValue(new SimpleDateFormat("yyyyMMddHHmmss").format(data));
            livingSubjectBirhtTime.getValue().add(birthTime);
            ST dataNascimentoST = new ST();
            dataNascimentoST.getContent().add("LivingSubject.birthTime");
            livingSubjectBirhtTime.setSemanticsText(dataNascimentoST);
            // </parameterList>
            // </queryByParameter>
        }
        if (usuario.getNome() != null) {
            //Nome
            PDQObjectFactory factory = new PDQObjectFactory();
            PRPAMT201306UV02LivingSubjectName livingSubjectNome = new PRPAMT201306UV02LivingSubjectName();
            parameterList.getLivingSubjectName().add(livingSubjectNome);
            EN nomeParametro = new EN();
            nomeParametro.getUse().add("L");
            EnGiven given = new EnGiven();
            given.getContent().add(usuario.getNome());
            nomeParametro.getContent().add(factory.createENGiven(given));
            livingSubjectNome.getValue().add(nomeParametro);
            ST nomeSemanticsText = new ST();
            nomeSemanticsText.getContent().add("LivingSubject.Given");
            livingSubjectNome.setSemanticsText(nomeSemanticsText);
        }

        if (usuario.getNomeMae() != null) {
            //Nome da mae
            PRPAMT201306UV02MothersMaidenName mothersMaidenName = new PRPAMT201306UV02MothersMaidenName();
            parameterList.getMothersMaidenName().add(mothersMaidenName);
            PN nomeMaeParametro = new PN();
            nomeMaeParametro.getUse().add("L");
            nomeMaeParametro.getContent().add(usuario.getNomeMae());
            mothersMaidenName.getValue().add(nomeMaeParametro);
            ST nomeMaeSemanticsText = new ST();
            nomeMaeSemanticsText.getContent().add("mothersMaidenName");
            mothersMaidenName.setSemanticsText(nomeMaeSemanticsText);
        }

        if (usuario.getCpf() != null) {
            //CPF
            PRPAMT201306UV02LivingSubjectId livingSubjectIdCPF = new PRPAMT201306UV02LivingSubjectId();
            parameterList.getLivingSubjectId().add(livingSubjectIdCPF);
            // <value root="2.16.840.1.113883.13.236" extension="708006399875323"/>
            // - PESQUISA POR CNS
            II cpfParameter = new II();
            cpfParameter.setRoot("2.16.840.1.113883.13.237");
            cpfParameter.setExtension(usuario.getCpf());
            livingSubjectIdCPF.getValue().add(cpfParameter);
            // <semanticsText>LivingSubject.id</semanticsText>
            ST cpfSemanticsText = new ST();
            cpfSemanticsText.getContent().add("LivingSubject.id");
            livingSubjectIdCPF.setSemanticsText(cpfSemanticsText);
        }

        if (usuario.getCns() != null) {
            // <livingSubjectId>
            PRPAMT201306UV02LivingSubjectId livingSubjectId = new PRPAMT201306UV02LivingSubjectId();
            parameterList.getLivingSubjectId().add(livingSubjectId);
            // <value root="2.16.840.1.113883.13.236" extension="708006399875323"/>
            // - PESQUISA POR CNS
            II cnsParameter = new II();
            cnsParameter.setRoot("2.16.840.1.113883.13.236");
            cnsParameter.setExtension(usuario.getCns());
            livingSubjectId.getValue().add(cnsParameter);
            // <semanticsText>LivingSubject.id</semanticsText>
            ST cnsSemanticsText = new ST();
            cnsSemanticsText.getContent().add("LivingSubject.id");
            livingSubjectId.setSemanticsText(cnsSemanticsText);
            // </livingSubjectId>

        }

        return obterUsuarioCadSus();

    }

    private List<TSUsuarioCadSus> obterUsuarioCadSus() {

        JAXBElement<PRPAMT201306UV02QueryByParameter> jaxbQuery = new PDQObjectFactory()
                .createPRPAIN201305UV02QUQIMT021001UV01ControlActProcessQueryByParameter(queryByParamenter);
        controlAct.setQueryByParameter(jaxbQuery);
        // </controlActProcess>

        PRPAIN201306UV02 retornoBody = pdq.pdqSupplierPRPAIN201305UV02(body);

        List<TSUsuarioCadSus> retorno = new ArrayList<TSUsuarioCadSus>();

        for (PRPAIN201306UV02MFMIMT700711UV01Subject1 subject : retornoBody.getControlActProcess().getSubject()) {

            PRPAMT201310UV02Person patientPerson = subject.getRegistrationEvent().getSubject1().getPatient()
                    .getPatientPerson().getValue();

            //@SuppressWarnings("unchecked")
            //JAXBElement<EnGiven> obj = (JAXBElement<EnGiven>) patientPerson.getName().get(0).getContent().get(0);
            TSUsuarioCadSus usuarioCadSus = new TSUsuarioCadSus();

            JAXBElement<EnGiven> obj;

            for (PN pn : patientPerson.getName()) {

                if (pn.getUse() != null && pn.getUse().size() > 0 && pn.getUse().get(0).equals("L")) { //nome

                    obj = (JAXBElement<EnGiven>) patientPerson.getName().get(0).getContent().get(0);
                    usuarioCadSus.setNome((String) obj.getValue().getContent().get(0));

                } else if (pn.getUse() != null && pn.getUse().size() > 0 && pn.getUse().get(0).equals("ASGN")) { //nome

                    obj = (JAXBElement<EnGiven>) patientPerson.getName().get(0).getContent().get(0);
                    usuarioCadSus.setNomeSocial((String) obj.getValue().getContent().get(0));
                }

            }

            //IDs
            String rg1 = "", rg2 = "";
            for (PRPAMT201310UV02OtherIDs otherId : patientPerson.getAsOtherIDs()) {
                for (II id : otherId.getId()) {

                    if ("2.16.840.1.113883.13.236".equals(id.getRoot())) { //CNS

                        usuarioCadSus.setCns(id.getExtension());

                    } else if ("2.16.840.1.113883.13.237".equals(id.getRoot())) { //CPF

                        usuarioCadSus.setCpf(id.getExtension());

                    } else if ("2.16.840.1.113883.13.243".equals(id.getRoot())) { //RG1

                        rg1 = id.getExtension();

                    } else if ("2.16.840.1.113883.13.245".equals(id.getRoot())) { //RG2

                        rg2 = id.getExtension();

                    } else if ("2.16.840.1.113883.13.244".equals(id.getRoot())) { //CTPS

                        usuarioCadSus.setCTPS(id.getExtension());

                    } else if ("2.16.840.1.113883.13.238".equals(id.getRoot())) { //CNH

                        usuarioCadSus.setCNH(id.getExtension());

                    } else if ("2.16.840.1.113883.13.239".equals(id.getRoot())) { //Titulo

                        usuarioCadSus.setTituloEleitor(id.getExtension());

                    } else if ("2.16.840.1.113883.13.240".equals(id.getRoot())) { //PIS

                        usuarioCadSus.setPis(id.getExtension());

                    }

                }

            }

            usuarioCadSus.setRg(rg1 + rg2);

            if (patientPerson.getAsCitizen() != null) {

                for (PRPAMT201310UV02Citizen city : patientPerson.getAsCitizen()) {

                    for (II id : city.getId()) {

                        if ("2.16.840.1.113883.4.330".equals(id.getRoot())) { //Passaporte
                            usuarioCadSus.setPassaporte(id.getExtension());
                            break;
                        }
                    }

                }
            }

            if (patientPerson.getTelecom() != null) {

                for (TEL tel : patientPerson.getTelecom()) {

                    if (tel.getUse() != null && tel.getUse().size() > 0) {

                        if (tel.getUse().get(0).equals("PRN")) {

                            usuarioCadSus.setTelefone(tel.getValue());

                        } else if (tel.getUse().get(0).equals("NET")) {

                            usuarioCadSus.setEmail(tel.getValue());

                        }
                    }

                }

            }

            for (PRPAMT201310UV02PersonalRelationship item : patientPerson.getPersonalRelationship()) {

                if (item.getCode().getCode().equals("PRN")) {
                    obj = (JAXBElement<EnGiven>) item.getRelationshipHolder1().getValue().getName().get(0).getContent().get(0);
                    usuarioCadSus.setNomeMae((String) obj.getValue().getContent().get(0));

                } else if (item.getCode().getCode().equals("NPRN")) {
                    obj = (JAXBElement<EnGiven>) item.getRelationshipHolder1().getValue().getName().get(0).getContent().get(0);
                    usuarioCadSus.setNomePai((String) obj.getValue().getContent().get(0));
                }

            }

            usuarioCadSus.setSexo(patientPerson.getAdministrativeGenderCode().getCode());

            TS dataNascimento = new TS();
            
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	    Date data = null;
            try {
                data = dtFormat.parse(patientPerson.getBirthTime().getValue());
            } catch (ParseException ex) {
                Logger.getLogger(TSPDQClient.class.getName()).log(Level.SEVERE, null, ex);
            }

            dataNascimento.setValue(new SimpleDateFormat("dd/MM/yyyy").format(data));

            usuarioCadSus.setDataNascimento(dataNascimento.getValue());

            usuarioCadSus.setRaca(patientPerson.getRaceCode().get(0).getCode());

            if (usuarioCadSus.getRaca().equals("05") && patientPerson.getEthnicGroupCode() != null && patientPerson.getEthnicGroupCode().size() > 0) { //fraca indigena

                usuarioCadSus.setEtnia(patientPerson.getEthnicGroupCode().get(0).getCode());

            }

            if (patientPerson.getBirthPlace().getValue().getClassCode().get(0).equals("BIRTHPL")) {

                for (Serializable elemento : patientPerson.getBirthPlace().getValue().getAddr().getContent()) {
                    Object objeto = ((JAXBElement) elemento).getValue();

                    if (objeto instanceof AdxpCity) {

                        usuarioCadSus.setCidade((String) ((AdxpCity) objeto).getContent().get(0));

                    } else if (objeto instanceof AdxpCountry) {

                        usuarioCadSus.setNacionalidade((String) ((AdxpCountry) objeto).getContent().get(0));

                    }
                }

            }

            if (patientPerson.getDeceasedInd() != null) {

                usuarioCadSus.setFlagVivo(patientPerson.getDeceasedInd().isValue());

            }

            usuarioCadSus.setEnderecoCadSus(new TSEnderecoCadSus());

            //Endereço
            for (AD endereco : patientPerson.getAddr()) {
                for (Serializable elemento : endereco.getContent()) {
                    Object objeto = ((JAXBElement) elemento).getValue();

                    if (objeto instanceof AdxpCity) {

                        usuarioCadSus.getEnderecoCadSus().setCidade((String) ((AdxpCity) objeto).getContent().get(0));

                    } else if (objeto instanceof AdxpCountry) {

                        usuarioCadSus.getEnderecoCadSus().setPais((String) ((AdxpCountry) objeto).getContent().get(0));

                    } else if (objeto instanceof AdxpState) {

                        usuarioCadSus.getEnderecoCadSus().setEstado((String) ((AdxpState) objeto).getContent().get(0));

                    } else if (objeto instanceof AdxpStreetName) {

                        usuarioCadSus.getEnderecoCadSus().setLogradouro((String) ((AdxpStreetName) objeto).getContent().get(0));

                    } else if (objeto instanceof AdxpStreetNameType) {

                        usuarioCadSus.getEnderecoCadSus().setTipoLogradouro((String) ((AdxpStreetNameType) objeto).getContent().get(0));

                    } else if (objeto instanceof AdxpAdditionalLocator) {

                        usuarioCadSus.getEnderecoCadSus().setBairro((String) ((AdxpAdditionalLocator) objeto).getContent().get(0));

                    } else if (objeto instanceof AdxpHouseNumber) {

                        usuarioCadSus.getEnderecoCadSus().setNumero((String) ((AdxpHouseNumber) objeto).getContent().get(0));

                    } else if (objeto instanceof AdxpPostalCode) {

                        usuarioCadSus.getEnderecoCadSus().setCep((String) ((AdxpPostalCode) objeto).getContent().get(0));

                    } else if (objeto instanceof AdxpUnitID) {

                        usuarioCadSus.getEnderecoCadSus().setComplemento((String) ((AdxpUnitID) objeto).getContent().get(0));

                    }

                }

            }

            retorno.add(usuarioCadSus);

        }

        return retorno;

    }

    public static void main(String args[]) {
        TSPDQClient client = new TSPDQClient();

        List<TSUsuarioCadSus> cadSusList = client.pesquisarCadSus(new TSUsuarioCadSus("SEVERINO FAUSTINO", "25/06/1938", null, null, null));

        for (TSUsuarioCadSus cadSus : cadSusList) {

            //UsuarioCadSus cadSus = client.pesquisarCadSus(new TSUsuarioCadSus(null, null, null, null, "898002940850595"));
            System.out.println("Nome:" + cadSus.getNome());
            System.out.println("NomeSocial:" + cadSus.getNomeSocial());
            System.out.println("NomeMae:" + cadSus.getNomeMae());
            System.out.println("NomePai:" + cadSus.getNomePai());
            System.out.println("CPF:" + cadSus.getCpf());
            System.out.println("CNS:" + cadSus.getCns());
            System.out.println("CNH:" + cadSus.getCNH());
            System.out.println("CTPS:" + cadSus.getCTPS());
            System.out.println("CidadeNasceu:" + cadSus.getCidade());
            System.out.println("Nacionalidade:" + cadSus.getNacionalidade());
            System.out.println("DataNascimento:" + cadSus.getDataNascimento());
            System.out.println("Email:" + cadSus.getEmail());
            System.out.println("Etnia:" + cadSus.getEtnia());
            System.out.println("IdExternoSistemaLegado:" + cadSus.getIdExterno());
            System.out.println("Passaporte:" + cadSus.getPassaporte());
            System.out.println("PIS:" + cadSus.getPis());
            System.out.println("Raca:" + cadSus.getRaca());
            System.out.println("RG:" + cadSus.getRg());
            System.out.println("Sexo:" + cadSus.getSexo());
            System.out.println("Telefone:" + cadSus.getTelefone());
            System.out.println("TituloEleitor:" + cadSus.getTituloEleitor());
            System.out.println("isVivo:" + cadSus.getFlagVivo());
            System.out.println("EnderecoTipoLogradouro:" + cadSus.getEnderecoCadSus().getTipoLogradouro());
            System.out.println("EnderecoLogradouro:" + cadSus.getEnderecoCadSus().getLogradouro());
            System.out.println("EnderecoNumero:" + cadSus.getEnderecoCadSus().getNumero());
            System.out.println("EnderecoBairro:" + cadSus.getEnderecoCadSus().getBairro());
            System.out.println("EnderecoComplemento:" + cadSus.getEnderecoCadSus().getComplemento());
            System.out.println("EnderecoCidade:" + cadSus.getEnderecoCadSus().getCidade());
            System.out.println("EnderecoEstado:" + cadSus.getEnderecoCadSus().getEstado());
            System.out.println("EnderecoPais:" + cadSus.getEnderecoCadSus().getPais());
            System.out.println("EnderecoCEP:" + cadSus.getEnderecoCadSus().getCep());
        }
    }

}
