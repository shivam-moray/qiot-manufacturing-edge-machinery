package io.qiot.manufacturing.edge.machinery.domain;

public enum ProductionChainStageEnum {
    /**
     * 
     */
    WEAVING("weaving"),
    /**
    * 
    */
    COLORING("coloring"),
    /**
    * 
    */
    PRINTING("printing"),
    /**
    * 
    */
    PACKAGING("packaging");

    private final String lcName;

    private ProductionChainStageEnum(String lcName) {
        this.lcName = lcName;
    }

    public String getLCName() {
        return lcName;
    }
}
