package com.kleegroup.formation.services.util;

import javax.inject.Inject;

import com.kleegroup.formation.domain.formation.Etat;

import io.vertigo.dynamo.domain.model.DtList;

/**
 * MasterDataStore for the static lists.
 * @author npiedeloup
 * @version $Id: TutoMasterDataStoreStatic.java,v 1.3 2014/06/27 12:21:39 pchretien Exp $
 */
public final class TutoMasterDataStoreStatic extends AbstractStaticMDDataStorePlugin {
	private static final String[] STATIC_REF = { "Catheter (T)", "Catheter, angioplasty (T)", "Catheter, angioplasty, atherectomy (P)", "Catheter, angioplasty, atherectomy, ablative (P)", "Catheter, angioplasty, balloon dilatation (P)", "Catheter, cardiac (T)", "Catheter, cardiac, ablation (P)", "Catheter, cardiac, balloon (T)", "Catheter, cardiac, balloon, intra-aortic (P)", "Catheter, cardiac, balloon, pacing electrode (P)", "Catheter, cardiac, balloon, pulmonary artery (P)", };

	@Inject
	public TutoMasterDataStoreStatic() {
		super("static");
	}

}
