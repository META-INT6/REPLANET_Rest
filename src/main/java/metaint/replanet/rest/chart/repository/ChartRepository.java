package metaint.replanet.rest.chart.repository;

import metaint.replanet.rest.chart.entity.CampaignDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChartRepository extends JpaRepository<CampaignDescription, Integer> {

    /* ------------ RepositoryTest start ------------- */

    @Query("SELECT DISTINCT campaignCategory FROM tblCampaignDescription")
    List<Object[]> findDistinctByCampaignCategory();

    @Query(value = "SELECT DISTINCT campaign_category FROM tbl_campaign_description"
            , nativeQuery = true)
    List<Object[]> findAllCampaingCategory();

    @Query(value = "SELECT cc.campaign_category, cc.campaigns, cc.sum_current_budget, cc.sum_goal_budget, " +
            "CASE WHEN cc.sum_current_budget > cc.sum_goal_budget THEN cc.sum_goal_budget " +
            "ELSE cc.sum_current_budget " +
            "END AS display_sum_current_budget " +
            "FROM ( " +
            "SELECT c.campaign_category, " +
            "COUNT(*) AS campaigns, " +
            "SUM(current_budget) AS sum_current_budget, " +
            "SUM(goal_budget) AS sum_goal_budget " +
            "FROM tbl_campaign_description c " +
            "GROUP BY campaign_category " +
            ") cc"
            , nativeQuery = true)
    List<Object[]> countAndSumByCategory();

    @Query(value = "SELECT DATE_FORMAT(start_date, '%Y-%m') AS monthly, COUNT(*) AS campaigns, " +
            "SUM(current_budget) AS sum_current_budget, " +
            "SUM(goal_budget) AS sum_goal_budget, " +
            "SUM(goal_budget-current_budget) AS sum_expect_budget " +
            "FROM tbl_campaign_description " +
            "WHERE DATE_FORMAT(start_date, '%Y-%m') BETWEEN '2023-01' AND '2023-12' " +
            "GROUP BY monthly"
            , nativeQuery = true)
    List<Object[]> countAndSumByCurrentyear();

    @Query(value = "SELECT DATE_FORMAT(start_date, '%Y-%m') AS monthly, COUNT(*) AS campaigns, " +
            "SUM(current_budget) AS sum_current_budget, " +
            "SUM(goal_budget) AS sum_goal_budget, " +
            "SUM(goal_budget-current_budget) AS sum_expect_budget " +
            "FROM tbl_campaign_description " +
            "WHERE DATE_FORMAT(start_date, '%Y-%m') BETWEEN '2022-01' AND '2022-12' " +
            "GROUP BY monthly"
            , nativeQuery = true)
    List<Object[]> countAndSumByPreviousyear();
    /* ------------ RepositoryTest END ------------- */



}
