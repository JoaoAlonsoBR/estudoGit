package br.com.jgm.api.io;

public class CampoLeiaute implements Comparable<CampoLeiaute>{

	private Integer indexCampo;
	private String nomeCampo;
	private String conteudoCampo;
	
	public CampoLeiaute(Integer indexCampo , String nomeCampo){
		this.indexCampo = indexCampo;
		this.nomeCampo = nomeCampo;
	}
	
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((indexCampo == null) ? 0 : indexCampo.hashCode());
		result = prime * result + ((nomeCampo == null) ? 0 : nomeCampo.hashCode());
		return result;
		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CampoLeiaute other = (CampoLeiaute) obj;
		if (indexCampo == null) {
			if (other.indexCampo != null)
				return false;
		} else if (!indexCampo.equals(other.indexCampo))
			return false;
		if (nomeCampo == null) {
			if (other.nomeCampo != null)
				return false;
		} else if (!nomeCampo.equals(other.nomeCampo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CampoLeiaute [indexCampo=" + indexCampo + ", nomeCampo=" + nomeCampo + ", conteudoCampo="
				+ conteudoCampo + "]";
	}

	public Integer getIndexCampo() {
		return indexCampo;
	}
	
	public void setIndexCampo(Integer indexCampo) {
		this.indexCampo = indexCampo;
	}	
	
	public String getNomeCampo() {
		return nomeCampo;
	}

	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}

	public String getConteudoCampo() {
		return conteudoCampo;
	}

	public void setConteudoCampo(String conteudoCampo) {
		this.conteudoCampo = conteudoCampo;
	}

	@Override
	public int compareTo(CampoLeiaute campo) {
		return indexCampo.compareTo(campo.getIndexCampo());
	}
	
}
