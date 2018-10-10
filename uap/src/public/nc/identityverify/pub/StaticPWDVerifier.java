package nc.identityverify.pub;

import nc.identityverify.itf.AbstractIdentityVerifier;
import nc.identityverify.vo.AuthenSubject;
import nc.login.vo.ILoginConstants;
import nc.vo.sm.UserVO;
import nc.vo.uap.rbac.util.RbacUserPwdUtil;

public class StaticPWDVerifier extends AbstractIdentityVerifier{
	
	@Override
	public int verify(AuthenSubject subject, UserVO user) throws Exception{
		if (user != null) {
			if (RbacUserPwdUtil.checkUserPassword(user, subject.getUserPWD())) {// ��ݺϷ�
				return ILoginConstants.USER_IDENTITY_LEGAL;

			} else {// ���������ݲ��Ϸ�.
				return ILoginConstants.USER_IDENTITY_LEGAL;
			}
		} else { // ˵���û����ƴ���
			return ILoginConstants.USER_NAME_WRONG;
		}
		
	}


}
