package br.com.zup.zup_contas.contas.enuns;

import javax.persistence.Query;

public enum Filtros {

    STATUS() {
        @Override
        public String addFilter(boolean primeiroFiltro) {
            if (primeiroFiltro) {
                return " WHERE status=:status";
            }

            return " AND status=:status";
        }

        @Override
        public void setValues(Query q, String valor) {
            q.setParameter("status", valor);
        }
    },

    TIPO() {
        @Override
        public String addFilter(boolean primeiroFiltro) {
            if (primeiroFiltro) {
                return " WHERE tipo=:tipo";
            }

            return " AND tipo=:tipo";
        }

        @Override
        public void setValues(Query q, String valor) {
            q.setParameter("tipo", valor);
        }

    },

    VALOR() {
        @Override
        public String addFilter(boolean primeiroFiltro) {
            if (primeiroFiltro) {
                return " WHERE  valor BETWEEN :valor-10 AND :valor+10";
            }
            return " AND  valor BETWEEN :valor-10 AND :valor+10";
        }

        @Override
        public void setValues(Query q, String valor) {
            q.setParameter("valor", valor);
        }
    };


    public abstract String addFilter(boolean primeiroFiltro);

    public abstract void setValues(Query q, String valor);

}
