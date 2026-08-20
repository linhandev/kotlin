// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: type-system, type-kinds, classifier-types, parameterized-classifier-types -> paragraph 27 -> sentence 27
 *                type-system, type-kinds, flexible-types, platform-types -> paragraph 27 -> sentence 27
 *                inheritance, inheriting -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: Java generic interfaces with erased List signatures remain distinct at Kotlin level; incomplete override reports ABSTRACT_MEMBER_NOT_IMPLEMENTED
 */

// FILE: JI56227.java
import java.util.List;

public interface JI56227 {
    void f(List<Integer> x);
}

// FILE: JK56227.java
import java.util.List;

public interface JK56227 {
    void f(List<String> x);
}

// FILE: main.kt
// TESTCASE NUMBER: 1
<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class C56227Missing<!> : JI56227, JK56227

<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class C56227OnlyInt<!> : JI56227, JK56227 {
    override fun f(x: List<Int>) {}
}
