package dev.fvames.pattern.factory.factoryMethod;

import dev.fvames.pattern.factory.simple.IProgramLanguage;

/**
 * 子类无限扩展，但是修改时互不影响
 */
public interface IFactoryMethod {

    IProgramLanguage create();
}
