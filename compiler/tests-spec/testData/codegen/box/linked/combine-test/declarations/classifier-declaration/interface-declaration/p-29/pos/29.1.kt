// WITH_STDLIB
// JVM_TARGET: 1.8

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 29 -> sentence 29
 *                type-system, type-kinds, flexible-types, platform-types -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: Kotlin class implementing Java interface may leave Java 8+ default methods uncovered; JVM supplies default
 */

// FILE: JavaWithDefault.java
public interface JavaWithDefault {
    default int defaultValue() {
        return 1;
    }

    int abstractValue();
}

// FILE: 29.1.kt

// TESTCASE NUMBER: 1
class InheritJavaDefault : JavaWithDefault {
    override fun abstractValue(): Int = 10
    // defaultValue() left uncovered — Java default method
}

class OverrideJavaDefault : JavaWithDefault {
    override fun abstractValue(): Int = 20
    override fun defaultValue(): Int = 3
}

fun box(): String {
    if (InheritJavaDefault().defaultValue() != 1) return "NOK: inherit-default"
    if (InheritJavaDefault().abstractValue() != 10) return "NOK: inherit-abstract"
    if (OverrideJavaDefault().defaultValue() != 3) return "NOK: override-default"
    if (OverrideJavaDefault().abstractValue() != 20) return "NOK: override-abstract"
    val asJava: JavaWithDefault = InheritJavaDefault()
    if (asJava.defaultValue() != 1) return "NOK: via-java-iface"
    val asJava2: JavaWithDefault = OverrideJavaDefault()
    if (asJava2.defaultValue() != 3) return "NOK: via-java-iface-override"
    return "OK"
}
