package com.joeahkim.chapaake.pages


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NotificationPage(modifier: Modifier = Modifier) {
    LazyColumn{
       item {
           Column (
               modifier = Modifier
                   .fillMaxWidth(),
               horizontalAlignment = Alignment.CenterHorizontally
           ){
               Text(
                   text = "Terms and Conditions",
                   fontSize = 24.sp,
                   fontWeight = FontWeight.SemiBold
               )
           }
           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(8.dp)
           ){
               Text(
                   text = "Welcome to Kash Betting Tips! These Terms and Conditions outline the rules and regulations for the use of our betting tips application. By accessing or using the App, you agree to comply with these terms. If you do not agree with any part of these terms, please do not use the App.",
                   fontSize = 16.sp,
                   fontWeight = FontWeight.Light
               )
           }

           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(10.dp)
           ) {
               Text(
                   text = "1. Acceptance of Terms",
                   fontSize = 20.sp,
                   fontWeight = FontWeight.Medium
               )
           }
           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(8.dp)
           ) {
               Text(
                   text = "By using Kash Betting Tips, you agree to these Terms and Conditions. If you do not agree, you must discontinue using the App immediately.",
                   fontSize = 16.sp,
                   fontWeight = FontWeight.Light
               )
           }
           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(10.dp)
           ) {
               Text(
                   text = "2. Eligibility",
                   fontSize = 20.sp,
                   fontWeight = FontWeight.Medium
               )
           }
           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(8.dp)
           ) {
               Text(
                   text = "To use this App, you must be at least 18 years old and have the legal capacity to enter into these Terms. By using the App, you represent and warrant that you meet these requirements.",
                   fontSize = 16.sp,
                   fontWeight = FontWeight.Light
               )
           }

           Column (
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(10.dp)
           ){
               Text(
                   text = "3. Betting Tips",
                   fontSize = 20.sp,
                   fontWeight = FontWeight.Medium
               )
           }
           Column (
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(8.dp)
           ){
               Text(
                   text = "The App provides betting tips and predictions based on statistical analysis and other relevant information. However, we do not guarantee the accuracy, completeness, or reliability of any betting tips or predictions provided through the App. You understand that all betting involves risk, and you are solely responsible for your betting decisions.",
                   fontSize = 16.sp,
                   fontWeight = FontWeight.Light
               )
           }

           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(10.dp)
           ) {
               Text(
                   text = "4. Limitation of Liability",
                   fontSize = 20.sp,
                   fontWeight = FontWeight.Medium
               )
           }
           Column (
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(8.dp)
           ){
               Text(
                   text = "To the maximum extent permitted by applicable law, Kash Betting Tips, its owners, employees, and agents shall not be liable for any direct, indirect, incidental, special, consequential, or punitive damages arising out of or related to your use of the App or any betting activities based on the tips provided.",
                   fontSize = 16.sp,
                   fontWeight = FontWeight.Light
               )
           }

           Column (
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(10.dp)
           ){
               Text(
                   text = "5. Responsible Gambling",
                   fontSize = 20.sp,
                   fontWeight = FontWeight.Medium
               )
           }
           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(8.dp)
           ) {
               Text(
                   text = "We encourage users to gamble responsibly. If you or someone you know has a gambling problem, please seek help from professional organizations that provide support for problem gambling. You can set limits on your betting and seek advice on how to gamble responsibly.",
                   fontSize = 16.sp,
                   fontWeight = FontWeight.Light
               )
           }

           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(10.dp)
           ) {
               Text(
                   text = "6. Modifications to the Terms",
                   fontSize = 20.sp,
                   fontWeight = FontWeight.Medium
               )
           }
           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(8.dp)
           ) {
               Text(
                   text = "We reserve the right to modify these Terms and Conditions at any time. Any changes will be effective immediately upon posting the revised terms on the App. Your continued use of the App after any changes constitutes your acceptance of the new Terms.",
                   fontSize = 16.sp,
                   fontWeight = FontWeight.Light
               )
           }

           Column (
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(10.dp)
           ){
               Text(
                   text = "7. Disclaimers",
                   fontSize = 20.sp,
                   fontWeight = FontWeight.Medium
               )
           }
           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(8.dp)
           ) {
               Text(
                   text = "No Warranty: The App is provided \"as is\" without any warranties of any kind. We do not warrant that the App will be uninterrupted, secure, or error-free.\n" +
                           "Limitation of Liability: To the maximum extent permitted by law, we are not liable for any indirect, incidental, special, or consequential damages arising from your use of the App or reliance on any content provided.",
                   fontSize = 16.sp,
                   fontWeight = FontWeight.Light
               )
           }



           Column (
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(10.dp)
           ){
               Text(
                   text = "8. Indemnification",
                   fontSize = 20.sp,
                   fontWeight = FontWeight.Medium
               )
           }
           Column (
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(8.dp)
           ){
               Text(
                   text = "You agree to indemnify and hold harmless Kash Betting Tips, its affiliates, and their respective officers, directors, employees, and agents from any claims, liabilities, damages, losses, or expenses (including reasonable attorneys' fees) arising out of or in connection with your use of the App or violation of these Terms.",
                   fontSize = 16.sp,
                   fontWeight = FontWeight.Light
               )
           }


       }
       }

}